package com.harry.web.service;

import com.harry.web.model.Role;

import java.util.List;

/**
 * Created by chenhaibo on 2017/11/24.
 */
public interface RoleService {
    /**
     * 通过用户id 查询用户 拥有的角色
     *
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Integer userId);
}
