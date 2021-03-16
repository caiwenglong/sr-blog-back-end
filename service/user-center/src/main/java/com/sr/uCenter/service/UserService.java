package com.sr.uCenter.service;

import com.sr.common.utils.RS;
import com.sr.uCenter.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sr.uCenter.entity.vo.LoginVo;
import com.sr.uCenter.entity.vo.ModifyVo;
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
    RS register(RegisterVo registerVo);

    // 获取用户信息
    UserInfoVo getUserInfo(HttpServletRequest token);

    // 修改密码
    RS modifyPassword(ModifyVo modifyVo);

    public RS logout();

}
