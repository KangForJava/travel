/**
 * Copyright (C), 2020-2021, 计算机科学与技术学院
 * FileName: CategoryDao
 * Author: kjy
 * Date: 2021/3/17 16:33
 * Description:
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
package cn.kjy.travel.dao;

import cn.kjy.travel.domain.Category;

import java.util.List;

/**
 * (一句话描述功能)<br>
 *
 *
 * @author kjy
 * @date 2021/3/17
 * @since 1.0.0
 */
public interface CategoryDao {

    /**
     * @return 从数据库查询到的category集合
     */
    public abstract List<Category> findAllCategory();
}
