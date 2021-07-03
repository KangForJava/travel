/*
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: RouteDao
 * Author: kjy
 * Date: 2021/3/17 21:01
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.dao;

import cn.kjy.travel.domain.Route;

import java.util.List;

/**
 * (一句话描述功能)<br>
 *
 *
 * @author kjy
 * @date 2021/3/17
 * @since 1.0.0
 */
public interface RouteDao {
    /**
     * @param cid 路线种类id
     * @param keyWord 查询关键词
     * @return 该种类的所有路线数量
     */
    public abstract int findTotalCount(int cid, String keyWord);

    /**
     * @param cid 路线种类id
     * @param keyWord 查询的关键词
     * @param start 查询总结果的截取索引开始位置
     * @param count 要查询出的路线数量
     * @return 路线集合
     */
    public abstract List<Route> findByPage(int cid, String keyWord, int start, int count);

    /**
     * @param rid 路线id
     * @return 查找到的路线对象
     */
    public abstract Route findRoute(int rid);


    /**
     * @param routeName 输入的路线描述字段
     * @param low 价格下区间下限
     * @param high 价格区间上限
     * @return 符合条件的已被收藏的路线数量
     */
    public abstract int findFavoriteTotalCount(String routeName, int low, int high);


    /**
     * @param routeName 输入的路线描述字段
     * @param low 价格区间下限
     * @param high 价格区间上限
     * @param start 查询总结果的截取索引开始位置
     * @param count 要查询出的路线数量
     * @return
     */
    public abstract List<Route> findFavoriteByPage(String routeName, int low, int high, int start, int count);
}
