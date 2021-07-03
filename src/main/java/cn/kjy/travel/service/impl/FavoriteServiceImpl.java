/*
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: FavoriteServiceImpl
 * Author: kjy
 * Date: 2021/3/18 22:48
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.service.impl;

import cn.kjy.travel.dao.FavoriteDao;
import cn.kjy.travel.dao.impl.FavoriteDaoImpl;
import cn.kjy.travel.domain.Favorite;
import cn.kjy.travel.domain.PageBean;
import cn.kjy.travel.service.FavoriteService;

import java.util.List;

/**
 * (一句话描述功能)<br>
 *
 *
 * @author kjy
 * @date 2021/3/18
 * @since 1.0.0
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public boolean isFavorite(String rid, int uid) {
        return favoriteDao.getFavoriteByRidAndUid(Integer.parseInt(rid), uid) != null;
    }

    @Override
    public boolean addFavorite(String rid, int uid) {
        int answer = favoriteDao.add(Integer.parseInt(rid), uid);
        return answer != 0;
    }

    @Override
    public PageBean findFavorite(int uid, String currentPage, String pageSize) {
        int curPage = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);
        int start = (curPage - 1) * size;

        PageBean<Favorite> pageBean = new PageBean<>();

        int count = favoriteDao.findCountByUid(uid);
        pageBean.setTotalCount(count);

        List<Favorite> favorites = favoriteDao.getUserFavoritePage(uid, start, size);
        pageBean.setRouteList(favorites);

        pageBean.setCurrentPage(curPage);
        pageBean.setPageSize(size);
        pageBean.setTotalPage(count % size == 0 ? count / size : count / size + 1);
        return pageBean;
    }
}
