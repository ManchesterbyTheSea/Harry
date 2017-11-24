package com.harry.web.service.impl;

import com.harry.web.dao.PermissionMapper;
import com.harry.web.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenhaibo on 2017/11/24.
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<String> selectPermissionNameByUserId(Integer userId) {
        return permissionMapper.selectPermissionNameByUserId(userId);
    }
}
