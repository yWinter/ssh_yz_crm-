package com.lanou.staff.service.impl;


import com.lanou.staff.dao.PostDao;
import com.lanou.staff.dao.impl.PostDaoImpl;
import com.lanou.staff.domain.Post;
import com.lanou.staff.service.PostService;

import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
public class PostServiceImpl implements PostService {
    private PostDao postDao;

    public PostServiceImpl() {
        postDao = new PostDaoImpl();
    }

    @Override
    public List<Post> findAll() {
        String hql = "from Post";
        return postDao.findAll(hql);
    }

    @Override
    public Post findById(int pid) {
        return postDao.findById(pid,Post.class);
    }
}
