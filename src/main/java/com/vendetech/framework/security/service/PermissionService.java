package com.vendetech.framework.security.service;

import com.vendetech.common.utils.ServletUtils;
import com.vendetech.common.utils.StringUtils;
import com.vendetech.framework.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 自定义权限实现，ss取自SpringSecurity首字母
 *
 * @author vendetech
 */
@Service("ss")
public class PermissionService {
    /**
     * 所有权限标识
     */
    private static final String ALL_PERMISSION = "*:*:*";

    /**
     * 管理员角色权限标识
     */
    private static final String SUPER_ADMIN = "admin";

    private static final String ROLE_DELIMETER = ",";

    private static final String PERMISSION_DELIMETER = ",";

    @Autowired
    private TokenService tokenService;

    /**
     * 判断用户是否拥有某个角色
     *
     * @param role 角色字符串
     * @return 用户是否具备某角色
     */
    public boolean hasRole(String role) {
        if (StringUtils.isEmpty(role)) {
            return false;
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return loginUser != null;
        // if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getUser().getRoles())) {
        //     return false;
        // }
        // for (SysRole sysRole : loginUser.getUser().getRoles()) {
        //     String roleKey = sysRole.getRoleKey();
        //     if (SUPER_ADMIN.contains(roleKey) || roleKey.contains(StringUtils.trim(role))) {
        //         return true;
        //     }
        // }
    }

    /**
     * 验证用户是否不具备某角色，与 isRole逻辑相反。
     *
     * @param role 角色名称
     * @return 用户是否不具备某角色
     */
    public boolean lacksRole(String role) {
        return hasRole(role) != true;
    }


    /**
     * 判断是否包含权限
     *
     * @param permissions 权限列表
     * @param permission  权限字符串
     * @return 用户是否具备某权限
     */
    private boolean hasPermissions(Set<String> permissions, String permission) {
        return permissions.contains(ALL_PERMISSION) || permissions.contains(StringUtils.trim(permission));
    }
}
