/*
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: PageBean
 * Author: kjy
 * Date: 2021/3/17 20:24
 * Description: this class is used to contain page message
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.domain;

import java.util.List;

/**
 * (一句话描述功能)<br>
 * this class is used to contain page message
 *
 * @author kjy
 * @date 2021/3/17
 * @since 1.0.0
 */
public class PageBean<T> {
    private int totalPage;
    private int totalCount;
    private int currentPage;
    private int pageSize;

    public List<T> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<T> routeList) {
        this.routeList = routeList;
    }

    private List<T> routeList;

    public PageBean(){

    }
    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
