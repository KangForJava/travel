/*
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: CategoryDaoImpl
 * Author: kjy
 * Date: 2021/3/17 16:35
 * Description: this class is used to run operations about category
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.dao.impl;

import cn.kjy.travel.dao.CategoryDao;
import cn.kjy.travel.domain.Category;
import cn.kjy.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * (一句话描述功能)<br>
 * this class is used to run operations about category
 *
 * @author kjy
 * @date 2021/3/17
 * @since 1.0.0
 */
public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAllCategory() {
        String sql = "select * from tab_category order by cid";
        List<Category> list = template.query(sql, new BeanPropertyRowMapper<>(Category.class));
        return list;
    }
}
