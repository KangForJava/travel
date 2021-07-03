/*
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: UserServiceImpl
 * Author: kjy
 * Date: 2021/3/13 15:27
 * Description: this class is used to run operations about user
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.service.impl;

import cn.kjy.travel.dao.UserDao;
import cn.kjy.travel.dao.impl.UserDaoImpl;
import cn.kjy.travel.domain.User;
import cn.kjy.travel.service.UserService;
import cn.kjy.travel.util.MailUtils;
import cn.kjy.travel.util.UuidUtil;

/**
 * (一句话描述功能)<br>
 * this class is used to run operations about user
 *
 * @author kjy
 * @date 2021/3/13
 * @since 1.0.0
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean regist(User user) {
        User finded = userDao.findUserByName(user.getUsername());
        if(finded == null){
            user.setStatus("N");
            String uuid = UuidUtil.getUuid();
            user.setCode(uuid);
            userDao.save(user);
            new Thread(() -> {
                String content = "<a href='http://localhost:80/travel/user/active?code=" + uuid + "'>黑马旅游网</a>";
                MailUtils.sendMail(user.getEmail(), content, "激活账号");
            }).start();
            return true;
        }
        return false;
    }

    @Override
    public boolean active(String code) {
        int result = userDao.activeUserStatus(code);
        return result != 0;
    }

    @Override
    public User login(User loginUser) {
        return userDao.findByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
    }
}
