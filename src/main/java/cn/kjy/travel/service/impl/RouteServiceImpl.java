/*
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: RouteServiceImpl
 * Author: kjy
 * Date: 2021/3/17 20:43
 * Description: this class is used to run operations about route
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.service.impl;

import cn.kjy.travel.dao.FavoriteDao;
import cn.kjy.travel.dao.RouteDao;
import cn.kjy.travel.dao.RouteImageDao;
import cn.kjy.travel.dao.SellerDao;
import cn.kjy.travel.dao.impl.FavoriteDaoImpl;
import cn.kjy.travel.dao.impl.RouteDaoImpl;
import cn.kjy.travel.dao.impl.RouteImageDaoImpl;
import cn.kjy.travel.dao.impl.SellerDaoImpl;
import cn.kjy.travel.domain.PageBean;
import cn.kjy.travel.domain.Route;
import cn.kjy.travel.domain.RouteImg;
import cn.kjy.travel.service.RouteService;

import java.util.List;

/**
 * (一句话描述功能)<br>
 * this class is used to run operations about route
 *
 * @author kjy
 * @date 2021/3/17
 * @since 1.0.0
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImageDao routeImageDao = new RouteImageDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean pageQuery(String cid, String keyWord, String currentPage, String pageSize) {
        int id = Integer.parseInt(cid);
        int curPage = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);

        PageBean<Route> pageBean = new PageBean<>();

        int totalCount = routeDao.findTotalCount(id, keyWord);
        pageBean.setTotalCount(totalCount);

        int start = (curPage - 1) * size;
        List<Route> routes = routeDao.findByPage(id, keyWord, start, size);
        pageBean.setRouteList(routes);

        pageBean.setCurrentPage(curPage);
        pageBean.setPageSize(size);
        pageBean.setTotalPage(totalCount % size == 0 ? totalCount / size : totalCount / size + 1);

        return pageBean;

    }

    @Override
    public Route findDetail(String rid) {
        Route route = routeDao.findRoute(Integer.parseInt(rid));
        List<RouteImg> routeImgList = routeImageDao.getImagesByRid(route.getRid());
        route.setRouteImgList(routeImgList);
        route.setSeller(sellerDao.getSellerById(route.getSid()));
        route.setCount(favoriteDao.findCountByRid(route.getRid()));
        return route;
    }

    @Override
    public PageBean favoritePageQuery(String currentPage, String pageSize, String routeName, String lowPrice, String highPrice) {
        int low, high;
        if(lowPrice == null || lowPrice.length() == 0){
            low = 0;
        }else{
            low = Integer.parseInt(lowPrice);
        }
        if(highPrice == null || highPrice.length() == 0){
            high = Integer.MAX_VALUE;
        }else{
            high = Integer.parseInt(highPrice);
        }
        int curPage = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);

        PageBean<Route> pageBean = new PageBean<>();
        pageBean.setTotalCount(routeDao.findFavoriteTotalCount(routeName, low, high));

        int start = (curPage - 1) * size;
        List<Route> routes = routeDao.findFavoriteByPage(routeName, low, high, start, size);
        pageBean.setRouteList(routes);

        pageBean.setPageSize(size);
        pageBean.setCurrentPage(curPage);

        int pages = pageBean.getTotalCount() / size;
        pageBean.setTotalPage(pageBean.getTotalCount() % size == 0 ? pages : pages + 1);

        return pageBean;
    }
}
