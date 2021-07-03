/*
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: CategoryServiceImpl
 * Author: kjy
 * Date: 2021/3/17 16:30
 * Description: this class is used to run operations about Category
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.service.impl;

import cn.kjy.travel.dao.CategoryDao;
import cn.kjy.travel.dao.impl.CategoryDaoImpl;
import cn.kjy.travel.domain.Category;
import cn.kjy.travel.service.CategoryService;
import cn.kjy.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * (一句话描述功能)<br>
 * this class is used to run operations about Category
 *
 * @author kjy
 * @date 2021/3/17
 * @since 1.0.0
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        List<Category> list;
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> categories = jedis.zrangeWithScores("category", 0, -1);
        if(categories == null || categories.size() == 0){
            list = categoryDao.findAllCategory();
            list.forEach(e -> jedis.zadd("category", e.getCid(), e.getCname()));
        }else{
            list = new ArrayList<>();
            categories.forEach(e -> list.add(new Category((int)e.getScore(), e.getElement())));
        }
        return list;
    }
}
