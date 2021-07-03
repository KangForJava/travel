/*
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: RouteDaoImpl
 * Author: kjy
 * Date: 2021/3/17 21:06
 * Description: this class is used to query and update route message
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.dao.impl;

import cn.kjy.travel.dao.RouteDao;
import cn.kjy.travel.domain.Route;
import cn.kjy.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * (一句话描述功能)<br>
 * this class is used to query and update route message
 *
 * @author kjy
 * @date 2021/3/17
 * @since 1.0.0
 */
public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid, String keyWord) {
        StringBuilder sb = new StringBuilder("select count(*) from tab_route where 1 = 1");
        List<Object> params = new ArrayList<>();
        if(cid != 0){
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if(keyWord != null && keyWord.length() > 0){
            sb.append(" and rname like ? ");
            params.add("%" + keyWord + "%");
        }
        return template.queryForObject(sb.toString(), Integer.class, params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, String keyWord, int start, int count) {
        String sql = "select * from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        if(cid != 0){
            params.add(cid);
            sb.append(" and cid = ? ");
        }
        if(keyWord != null && keyWord.length() > 0){
            sb.append(" and rname like ? ");
            params.add("%" + keyWord + "%");
        }
        sb.append(" limit ?, ?");
        params.add(start);
        params.add(count);
        return template.query(sb.toString(), new BeanPropertyRowMapper<>(Route.class), params.toArray());

    }

    @Override
    public Route findRoute(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
    }

    @Override
    public int findFavoriteTotalCount(String routeName, int low, int high) {
        String sql = "select count(*) from tab_route t1 inner join (select rid , " +
                "count(rid) freq from tab_favorite group by rid) t2 on " +
                "t1.rid = t2.rid where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        if(routeName != null && routeName.length() > 0){
            sb.append(" and t1.rname like ? ");
            params.add("%" + routeName + "%");
        }
        sb.append(" and t1.price between ? and ?");
        params.add(low);
        params.add(high);
        return template.queryForObject(sb.toString(), Integer.class, params.toArray());
    }

    @Override
    public List<Route> findFavoriteByPage(String routeName, int low, int high, int start, int count) {
        String sql = "select t1.rid rid, t1.rname rname, t1.price price, t1.rimage rimage, t2.freq count "+
                "from tab_route t1 inner join (select rid , count(rid) freq from tab_favorite group by rid) t2 "+
                "on t1.rid = t2.rid where 1 = 1";
        List<Object> params = new ArrayList<>();
        StringBuilder sb = new StringBuilder(sql);
        if(routeName != null && routeName.length() > 0){
            sb.append(" and t1.rname like ? ");
            params.add("%" + routeName + "%");
        }
        sb.append(" and t1.price between ? and ? order by t2.freq desc limit ?, ?");
        params.add(low);
        params.add(high);
        params.add(start);
        params.add(count);

        return template.query(sb.toString(), new BeanPropertyRowMapper<>(Route.class), params.toArray());
    }
}
