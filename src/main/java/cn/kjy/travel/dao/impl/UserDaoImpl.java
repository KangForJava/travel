/*
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: UserDaoImpl
 * Author: kjy
 * Date: 2021/3/13 15:10
 * Description: this class is used to query and update user message
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.dao.impl;

import cn.kjy.travel.dao.UserDao;
import cn.kjy.travel.domain.User;
import cn.kjy.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * (一句话描述功能)<br>
 * this class is used to query and update user message
 *
 * @author kjy
 * @date 2021/3/13
 * @since 1.0.0
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findUserByName(String username) {
        String sql = "select * from tab_user where username = ?";
        User user = null;
        try{
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        }catch (Exception e){
            //e.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(User user) {
        String sql = "insert into tab_user (username, password, name, birthday, sex, telephone, email, status, code)" +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(),
                user.getSex(), user.getTelephone(), user.getEmail(), user.getStatus(), user.getCode());
    }

    @Override
    public int activeUserStatus(String userCode) {
        String sql = "update tab_user set status = ? where code = ?";
        return template.update(sql, "Y", userCode);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "select * from tab_user where username = ? and password = ? and status = ?";
        User user = null;
        try{
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password, "Y");
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
