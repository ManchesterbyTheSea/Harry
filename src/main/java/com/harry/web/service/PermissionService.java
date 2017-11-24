package com.harry.web.service;

import java.util.List;

/**
 * Created by chenhaibo on 2017/11/24.
 */
public interface PermissionService {

    List<String> selectPermissionNameByUserId(Integer userId);
}
