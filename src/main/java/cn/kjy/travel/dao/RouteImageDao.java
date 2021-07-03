/**
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: RouteImageDao
 * Author: kjy
 * Date: 2021/3/18 20:10
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.dao;

import cn.kjy.travel.domain.RouteImg;

import java.util.List;

/**
 * (一句话描述功能)<br>
 *
 *
 * @author kjy
 * @date 2021/3/18
 * @since 1.0.0
 */
public interface RouteImageDao {
    /**
     * @param rid 路线id
     * @return 该路线对应的图片集合
     */
    public abstract List<RouteImg> getImagesByRid(int rid);
}
