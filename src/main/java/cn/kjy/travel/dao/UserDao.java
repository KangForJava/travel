/*
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: UserDao
 * Author: kjy
 * Date: 2021/3/13 15:06
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.dao;

import cn.kjy.travel.domain.User;

/**
 * (一句话描述功能)<br>
 *
 *
 * @author kjy
 * @date 2021/3/13
 * @since 1.0.0
 */
public interface UserDao {
    /**
     * @param username 要查找的用户名
     * @return User 查找到的用户信息
     */
    public abstract User findUserByName(String username);

    /**
     * @param user 包含用户信息的对象
     */
    public abstract void save(User user);

    /**
     * @param userCode 用户的激活码
     * @return 激活结果
     */
    public abstract int activeUserStatus(String userCode);

    /**
     * @param username 输入的用户名
     * @param password 输入的密码
     * @return 从数据库查询出的用户对象
     */
    public abstract User findByUsernameAndPassword(String username, String password);
}
