/*
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: UserService
 * Author: kjy
 * Date: 2021/3/13 15:25
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.service;

import cn.kjy.travel.domain.User;

/**
 * (一句话描述功能)<br>
 *
 *
 * @author kjy
 * @date 2021/3/13
 * @since 1.0.0
 */
public interface UserService {
    /**
     * @param user 要注册的用户信息
     * @return 注册操作结果
     */
    public abstract boolean regist(User user);

    /**
     * @param code 用户识别码
     * @return  用户状态激活结果
     */
    public abstract boolean active(String code);

    /**
     * @param loginUser 前端输入的信息包装成的User
     * @return 数据库中查询出的User信息
     */
    public abstract User login(User loginUser);
}
