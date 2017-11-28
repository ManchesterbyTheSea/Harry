package com.harry.web.service;

import com.harry.web.model.User;

/**
 * Created by chenhaibo on 2017/11/9.
 */
public interface UserService {

    /**
     * 用户验证
     *
     * @param user
     * @return
     */
    User authentication(User user);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 创建用户
     * @param record
     * @return
     */
    int insert(User record);
}
