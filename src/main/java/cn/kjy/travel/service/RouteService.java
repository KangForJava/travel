/**
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: RouteService
 * Author: kjy
 * Date: 2021/3/17 20:38
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.service;

import cn.kjy.travel.domain.PageBean;
import cn.kjy.travel.domain.Route;

/**
 * (一句话描述功能)<br>
 *
 *
 * @author kjy
 * @date 2021/3/17
 * @since 1.0.0
 */
public interface RouteService {
    /**
     * @param cid 查询的路线种类
     * @param currentPage 查询的路线结果页码
     * @param pageSize 每页显示的路线数
     * @return 包含页面信息的对象
     */
    public abstract PageBean pageQuery(String cid, String keyWord, String currentPage, String pageSize);

    /**
     * @param rid 查询的路线id
     * @return 包含路线详细信息的路线对象
     */
    public abstract Route findDetail(String rid);


    /**
     * @param currentPage 查询的路线结果页码
     * @param pageSize 每页显示的路线数
     * @param routeName 输入的路线描述字段
     * @param lowPrice 价格区间下限
     * @param highPrice 价格区间上限
     * @return 包含页面信息的对象
     */
    public abstract PageBean favoritePageQuery(String currentPage, String pageSize, String routeName, String lowPrice, String highPrice);
}
