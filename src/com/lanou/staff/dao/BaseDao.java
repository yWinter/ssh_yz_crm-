package com.lanou.staff.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by 蓝鸥科技有限公司  www.lanou3g.com.
 */
public interface BaseDao<T> {

    /**
     * 保存某个对象
     * @param t 要保存的对象
     * @return 保存之后的对象
     **/
    T save(T t);

    /**
     * 删除某个对象
     *
     * @param t 要删除的对象,此对象必须是持久化状态的对象
     */
    void delete(T t);

    /**
     * 更新某个对象
     * @param t 要更新的对象
     */
    void update(T t);

    /**
     * 根据主键id 查询某个对象
     * @param id  要查询的主键 id
     * @param tClass 返回对象的类声明
     */

    T findById(Serializable id, Class<T> tClass);


    /**
     * 查询所有，返回查询到的结果集合
     *
     * @return 查询到的结果集合
     **/
    List<T> findAll(String hql);

    /**
     * 根据条件查询，返回查询到的结果集合
     *
     * @param hql    查询语句
     * @param params 查询语句的参数列表
     * @return 查询到的结果集合
     **/
    List<T> find(String hql, Map<String, Object> params);

    /**
     * 根据条件查询，返回查询到的第一个对象
     *
     * @param hql    查询语句
     * @param params 查询语句的参数列表
     * @return 第一个查询到的对象
     **/
    T findSingle(String hql, Map<String, Object> params);

}
