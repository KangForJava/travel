/*
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: FavoriteDaoImpl
 * Author: kjy
 * Date: 2021/3/18 22:52
 * Description: this class is used to operate message about favorite
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.dao.impl;

import cn.kjy.travel.dao.FavoriteDao;
import cn.kjy.travel.domain.Favorite;
import cn.kjy.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * (一句话描述功能)<br>
 * this class is used to operate message about favorite
 *
 * @author kjy
 * @date 2021/3/18
 * @since 1.0.0
 */
public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite getFavoriteByRidAndUid(int rid, int uid) {
        String sql = "select * from tab_favorite where rid = ? and uid = ?";
        Favorite favorite = null;
        try{
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class), rid, uid);
        }catch (Exception e){
            //不打印未查询的异常
        }
        return favorite;
    }

    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ?";
        return template.queryForObject(sql, Integer.class, rid);
    }

    @Override
    public int add(int rid, int uid) {
        String sql = "insert into tab_favorite values (?, now(), ?)";
        return template.update(sql, rid, uid);
    }

    @Override
    public int findCountByUid(int uid) {
        String sql = "select count(*) from tab_favorite where uid = ?";
        return template.queryForObject(sql, Integer.class, uid);
    }

    @Override
    public List<Favorite> getUserFavoritePage(int uid, int start, int size) {
        String sql = "select t1.*, t2.date from tab_route t1 inner join "+
                "(select rid, date from tab_favorite where uid = ?) t2 on "+
                "t1.rid = t2.rid order by t2.date desc limit ?, ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Favorite.class), uid, start, size);
    }
}
