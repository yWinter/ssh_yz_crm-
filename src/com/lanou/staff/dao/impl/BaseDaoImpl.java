package com.lanou.staff.dao.impl;


import com.lanou.staff.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by 蓝鸥科技有限公司  www.lanou3g.com.
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
    private  SessionFactory sessionFactory;
    private Class<T> beanClass;

    public BaseDaoImpl() {

        // this ,在运行时表示的【当前运行类】。在编译时表示就是【当前类】
        // 1 获得当前运行类的父类，父类具有泛型信息，
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        // 2 获得实际参数
        beanClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }


    @Override
    public T save(T t) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(t);
        transaction.commit();// 提交事务

        return t;
    }

    @Override
    public void delete(T t) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(t);

        transaction.commit();
    }

    @Override
    public void update(T t) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.update(t);// 更新某个对象

        transaction.commit();
    }


    @Override
    public T findById(Serializable id, Class<T> tClass) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        //根据主键 id 查询某个对象
        T t = (T) session.get(tClass,id);
        transaction.commit();
        return t;
    }

    @Override
    public List<T> findAll(String hql) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();


        //执行查询语句
        Query query = session.createQuery(hql);

        List<T> tList = query.list();

        transaction.commit();
        return tList;//返回查询结果
    }

    @Override
    public List<T> find(String hql, Map<String, Object> params) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        //执行查询语句
        Query query = session.createQuery(hql);

        //设置查询列表
        if (params != null && !params.isEmpty()) {
            //遍历参数
            for (String key : params.keySet()) {
                //设置查询语句中的key与value
                query.setParameter(key, params.get(key));
            }
        }

        List<T> tList = query.list();

        transaction.commit();
        return tList;//返回查询结果
    }

    @Override
    public T findSingle(String hql, Map<String, Object> params) {
        List<T> tList = find(hql, params);//调用条件查询
        if (tList.size() > 0) {
            return tList.get(0);//返回查询结果集的第一个对象
        }
        return null;//查询结果集为空时返回null
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
