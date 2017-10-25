package com.lanou.staff.service.impl;

import com.lanou.staff.dao.DepartmentDao;
import com.lanou.staff.dao.impl.DepartmentDaoImpl;
import com.lanou.staff.domain.Department;
import com.lanou.staff.service.DepartmentService;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/20.
 */
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao;

    public DepartmentServiceImpl() {
        departmentDao = new DepartmentDaoImpl();
    }

    @Override
    public List<Department> findAll() {
        String hql = "from Department";
        return departmentDao.findAll(hql);
    }


    @Override
    public List<Department> find(String hql, Map<String, Object> param) {
        return departmentDao.find(hql,param);
    }
}
