/**
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: FavoriteDao
 * Author: kjy
 * Date: 2021/3/18 22:50
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.dao;

import cn.kjy.travel.domain.Favorite;

import java.util.List;

/**
 * (一句话描述功能)<br>
 *
 *
 * @author kjy
 * @date 2021/3/18
 * @since 1.0.0
 */
public interface FavoriteDao {
    /**
     * @param rid 路线id
     * @param uid 用户id
     * @return 查询出的Favorite对象
     */
    public abstract Favorite getFavoriteByRidAndUid(int rid, int uid);

    /**
     * @param rid 路线id
     * @return 该路线被收藏的次数
     */
    public abstract int findCountByRid(int rid);

    /**
     * @param rid 路线id
     * @param uid 用户id
     * @return 添加收藏的结果
     */
    public abstract int add(int rid, int uid);

    /**
     * @param uid 用户id
     * @return 用户收藏的数量
     */
    public abstract int findCountByUid(int uid);

    /**
     * @param uid 用户id
     * @param start 从所有结果中开始截取的索引
     * @param size 查询的结果集大小
     * @return 收藏路线的集合
     */
    public abstract List<Favorite> getUserFavoritePage(int uid, int start, int size);
}
