package com.harry.web.service.impl;

import com.harry.web.dao.UserMapper;
import com.harry.web.model.User;
import com.harry.web.model.UserExample;
import com.harry.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenhaibo on 2017/11/9.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User authentication(User user) {
        return null;
    }

    @Override
    public User selectByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public int insert(User record) {
        return userMapper.insertSelective(record);
    }
}
