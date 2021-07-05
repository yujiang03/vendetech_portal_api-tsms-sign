package com.vendetech.controller;

import com.taobao.api.ApiException;
import com.vendetech.common.constant.Constants;
import com.vendetech.common.utils.ServletUtils;
import com.vendetech.framework.dingtalk.ali.AuthHelper;
import com.vendetech.framework.security.LoginUser;
import com.vendetech.framework.security.service.SysLoginService;
import com.vendetech.framework.security.service.TokenService;
import com.vendetech.framework.web.domain.AjaxResult;
import com.vendetech.vo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 登录验证
 *
 * @author vendetech
 */
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(String username, String password, String code, String uuid) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(username, password, code, uuid);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @PostMapping("/loginByDingTalkScan")
    public AjaxResult loginByDingTalkScan(HttpServletRequest request, @RequestBody HashMap<String, Object> param)
            throws ApiException {
        AjaxResult ajax = AjaxResult.success();
        String tmpCode = param.get("tmpAuthCode").toString();
        String username = null;
        username = AuthHelper.getUserInfo(tmpCode);
        // 用户登录
        String token = loginService.loginByDingTalkScan(username);
        // 获取登录token
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @PostMapping("/loginByDingTalkPc")
    public AjaxResult loginByDingTalkPc(HttpServletRequest request, @RequestBody HashMap<String, Object> param)
            throws ApiException {
        AjaxResult ajax = AjaxResult.success();
        String tmpCode = param.get("tmpAuthCode").toString();
        String username = null;
        username = AuthHelper.getAppUserInfo(tmpCode);
        // 用户登录
        String token = loginService.loginByDingTalkScan(username);
        // 获取登录token
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        return ajax;
    }


    // /**
    //  * 获取路由信息
    //  *
    //  * @return 路由信息
    //  */
    // @GetMapping("getRouters")
    // public AjaxResult getRouters()
    // {
    //     LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
    //     // 用户信息
    //     SysUser user = loginUser.getUser();
    //     List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
    //     return AjaxResult.success(menuService.buildMenus(menus));
    // }
}
