package com.lanou.staff.test;


import com.lanou.staff.dao.DepartmentDao;
import com.lanou.staff.dao.PostDao;
import com.lanou.staff.dao.StaffDao;
import com.lanou.staff.dao.impl.DepartmentDaoImpl;
import com.lanou.staff.dao.impl.PostDaoImpl;
import com.lanou.staff.dao.impl.StaffDaoImpl;
import com.lanou.staff.domain.Department;
import com.lanou.staff.domain.Post;
import com.lanou.staff.domain.Staff;
import com.lanou.staff.service.DepartmentService;
import com.lanou.staff.service.impl.DepartmentServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * Created by 蓝鸥科技有限公司  www.lanou3g.com.
 */
public class MainTest {
    private SessionFactory sessionFactory;


    /**
     * 插入原始数据
     **/
    @Test
    public void save() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        //创建数据
        Department department = new Department("教学部");
        Post post = new Post("教学总监");
        Post post1 = new Post("Java主管");
        Post post2 = new Post("Java讲师");

        department.getPosts().add(post);
        department.getPosts().add(post1);
        department.getPosts().add(post2);

        session.save(department);//保存教学部

        Department department1 = new Department("职规部");
        Post post3 = new Post("职规主管");
        Post post4 = new Post("班主任");

        department1.getPosts().add(post3);
        department1.getPosts().add(post4);

        session.save(department1);//保存职规部

        /**
         * 教学部
         */
        // 1
        Staff staff1 = new Staff("李忠仁");
        staff1.setPostID(post);//教学总监
        session.save(staff1);//保存李忠仁

        // 2
        Staff staff2 = new Staff("萌小义");
        staff2.setPostID(post1);//班主任
        session.save(staff2);//保存萌小义
        // 3
        Staff staff3 = new Staff("大表姐");
        staff3.setPostID(post2);//java讲师
        session.save(staff3);//保存大表姐
        // 4
        Staff staff4 = new Staff("武神");
        staff4.setPostID(post2);//java讲师
        session.save(staff4);//保存武神

        /**
         * 职规部
         */
        // 1
        Staff staff5 = new Staff("马琳");
        staff5.setPostID(post3);//职规主管
        session.save(staff5);//保存马琳
        // 2
        Staff staff6 = new Staff("欣姐");
        staff6.setPostID(post4);//班主任
        session.save(staff6);//保存欣姐
        // 3
        Staff staff7 = new Staff("竹青姐");
        staff7.setPostID(post4);//班主任
        session.save(staff7);//保存竹青姐

        transaction.commit();
    }


    /**
     * dao层的单元测试
     **/
    @Test
    public void testDao() {
        //查询部门集合
        DepartmentDao departDao = new DepartmentDaoImpl();

        List<Department> departments = departDao
                .findAll("from Department");

        for (Department de : departments) {
            System.out.println(de);
        }

        //查询职务表中的所有数据
        PostDao postDao = new PostDaoImpl();
        List<Post> posts = postDao.find(
                "from Post", null);

        for (Post post : posts) {
            System.out.println(post);
        }

        //查询员工表中的所有数据
        StaffDao staffDao = new StaffDaoImpl();
        List<Staff> staffs = staffDao.find(
                "from Staff", null);
        for (Staff staff : staffs) {
            System.out.println(staff + "  " + staff.getPostID());
        }

        //查询名字叫大表姐的员工
        String hql = "from Staff where sname=:name";
        Map<String, Object> params = new HashMap<>();
        params.put("name", "大表姐");

        Staff staff = staffDao.findSingle(hql, params);
        System.out.println(staff);
    }

    @Test
    public void testService(){
        DepartmentService departService = new DepartmentServiceImpl();
        List<Department> departments = departService.findAll();

        for (Department depart : departments){
            System.out.println("基础属性:" +depart);

            for (Post post : depart.getPosts()){
                System.out.println("职务:" + post);
            }
        }
    }
}
