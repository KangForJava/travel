/*
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: SellerDao
 * Author: kjy
 * Date: 2021/3/18 20:28
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.dao;

import cn.kjy.travel.domain.Seller;

/**
 * (一句话描述功能)<br>
 *
 *
 * @author kjy
 * @date 2021/3/18
 * @since 1.0.0
 */
public interface SellerDao {
    /**
     * @param id 卖家id
     * @return 查找到的Seller对象
     */
    public abstract Seller getSellerById(int id);
}
