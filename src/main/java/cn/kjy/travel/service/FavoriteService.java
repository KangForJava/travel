/**
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: FavoriteService
 * Author: kjy
 * Date: 2021/3/18 22:46
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.service;

import cn.kjy.travel.domain.PageBean;

/**
 * (一句话描述功能)<br>
 *
 *
 * @author kjy
 * @date 2021/3/18
 * @since 1.0.0
 */
public interface FavoriteService {
    /**
     * @param rid 路线id
     * @param uid 用户id
     * @return 是否是用户的收藏路线
     */
    public abstract boolean isFavorite(String rid, int uid);


    /**
     * @param rid 路线id
     * @param uid 当前用户id
     * @return 是否成功添加收藏
     */
    public abstract boolean addFavorite(String rid, int uid);

    /**
     * @param uid 用户id
     * @param currentPage 当前页码
     * @param pageSize 页面包含的路线对象数量
     * @return 包含页面信息的对象
     */
    public abstract PageBean findFavorite(int uid, String currentPage, String pageSize);
}
