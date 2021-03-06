package com.harry.web.security;

import com.harry.web.model.User;
import com.harry.web.service.PermissionService;
import com.harry.web.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限信息验证
 * Created by chenhaibo on 2017/11/24.
 */
public class AuthRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(AuthRealm.class);

    @Resource
    private UserService userService;

    @Resource
    private PermissionService permissionService;


    /**
     * 授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String)super.getAvailablePrincipal(principalCollection);

        User user = userService.selectByUsername(loginName);
        if (user != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

            List<String> permissionNames = permissionService.selectPermissionNameByUserId(user.getId());
            if (permissionNames != null && permissionNames.size() > 0) {
                info.addStringPermissions(permissionNames);
            }
            return info;
        }
        return null;
    }

    /**
     * 验证用户身份
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;

        //查出是否有此用户
        User user=userService.selectByUsername(token.getUsername());
        ByteSource credentialsSalt = ByteSource.Util.bytes(token.getUsername());

        if(user != null){
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), credentialsSalt, getName());
        }
        return null;
    }
}
