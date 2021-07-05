package com.vendetech.controller;

import com.vendetech.framework.security.LoginUser;
import com.vendetech.framework.security.service.TokenService;
import com.vendetech.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private TokenService tokenService;
    @Value("${portal.host.front}")
    String frontEndHost;

    @GetMapping("/isLoggedIn")
    public R isLoggedIn(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        return R.data(loginUser);
    }

    @GetMapping("/loginUrl")
    public R getLoginUrl() {

        return R.ok(frontEndHost + "/user/login");
    }

}
