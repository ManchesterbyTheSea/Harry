package com.harry.web.service.impl;

import com.harry.web.dao.RoleMapper;
import com.harry.web.model.Role;
import com.harry.web.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenhaibo on 2017/11/24.
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectRolesByUserId(Integer userId) {
        return roleMapper.selectRolesByUserId(userId);
    }
}
