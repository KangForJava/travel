/*
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: RouteImageDaoImpl
 * Author: kjy
 * Date: 2021/3/18 20:14
 * Description: this class is used to operate images about a route
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.dao.impl;

import cn.kjy.travel.dao.RouteImageDao;
import cn.kjy.travel.domain.RouteImg;
import cn.kjy.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * (一句话描述功能)<br>
 * this class is used to operate images about a route
 *
 * @author kjy
 * @date 2021/3/18
 * @since 1.0.0
 */
public class RouteImageDaoImpl implements RouteImageDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<RouteImg> getImagesByRid(int rid) {
        String sql = "select * from tab_route_img where rid = ?";

        return template.query(sql, new BeanPropertyRowMapper<>(RouteImg.class), rid);
    }
}
