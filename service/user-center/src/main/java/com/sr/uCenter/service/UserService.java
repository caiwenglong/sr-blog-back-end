package com.sr.uCenter.service;

import com.sr.common.utils.RS;
import com.sr.uCenter.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sr.uCenter.entity.vo.LoginVo;
import com.sr.uCenter.entity.vo.RegisterVo;
import com.sr.uCenter.entity.vo.UserInfoVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caiwenlong
 * @since 2020-11-13
 */
public interface UserService extends IService<User> {

    // 登录方法
    RS login(LoginVo user);

    // 注册方法
    void register(RegisterVo registerVo);

    // 获取用户信息
    UserInfoVo getUserInfo(HttpServletRequest token);

    public RS logout();

}
