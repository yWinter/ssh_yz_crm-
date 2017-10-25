package com.lanou.staff.service;


import com.lanou.staff.domain.Department;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/20.
 */
public interface DepartmentService {

    List<Department> findAll();



    List<Department> find(String hql, Map<String, Object> param);

}
