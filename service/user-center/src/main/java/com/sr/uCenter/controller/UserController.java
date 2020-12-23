package com.sr.uCenter.controller;


import com.sr.common.utils.RS;
import com.sr.uCenter.entity.vo.LoginVo;
import com.sr.uCenter.entity.vo.RegisterVo;
import com.sr.uCenter.entity.vo.UserInfoVo;
import com.sr.uCenter.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caiwenlong
 * @since 2020-11-13
 */
@RestController
@RequestMapping("/user-center/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public RS login(
            @ApiParam(name = "username", value = "登录的用户名密码")
            @RequestBody LoginVo user) {
        return userService.login(user);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public RS registerUser(
            @ApiParam(name = "registerVo", value = "注册的用户对")
            @RequestBody RegisterVo registerVo
    ) {
        userService.register(registerVo);
        return RS.success();
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/getInfo")
    public RS getInfo(
            @ApiParam(name = "token", value = "token")
                    HttpServletRequest request
    ) {
        UserInfoVo userInfo = userService.getUserInfo(request);
        return RS.success().data("result", userInfo);
    }

    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    public RS logout() {
        return userService.logout();
    }
}

