package com.blog.mapper;

import com.blog.model.User;

/**
 * Created by hc on 2019/3/30.
 */
public interface UserMapper {
    int add(User user);
    User findOne(User user);
//    User
}
