/**
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: SellerDaoImpl
 * Author: kjy
 * Date: 2021/3/18 20:30
 * Description: this class is used to operate message about seller
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.dao.impl;

import cn.kjy.travel.dao.SellerDao;
import cn.kjy.travel.domain.Seller;
import cn.kjy.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * (一句话描述功能)<br>
 * this class is used to operate message about seller
 *
 * @author kjy
 * @date 2021/3/18
 * @since 1.0.0
 */
public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller getSellerById(int id) {
        String sql = "select * from tab_seller where sid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Seller.class), id);
    }
}
