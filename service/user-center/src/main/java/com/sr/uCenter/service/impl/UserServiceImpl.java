package com.sr.uCenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sr.common.utils.JwtUtils;
import com.sr.common.utils.MD5;
import com.sr.common.utils.RS;
import com.sr.service.base.exception.CustomException;
import com.sr.uCenter.entity.User;
import com.sr.uCenter.entity.vo.LoginVo;
import com.sr.uCenter.entity.vo.RegisterVo;
import com.sr.uCenter.entity.vo.UserInfoVo;
import com.sr.uCenter.mapper.UserMapper;
import com.sr.uCenter.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caiwenlong
 * @since 2020-11-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    // 登录功能
    @Override
    public RS login(LoginVo user) {
        String mobile = user.getUsername();
        String password = user.getPassword();

        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            return loginError("SR20002", "登录失败！手机号或者密码不能为空");
        }

        User hitUser = hitUser(mobile);

        if(hitUser == null || hitUser.getIsDelete()) {
            return loginError("SR20005", "登录失败！该账号不存在");
        }

        if(!MD5.encrypt(password).equals(hitUser.getPassword())) {
            return loginError("SR20003", "登录失败！密码错误");
        }

        if(hitUser.getIsDisabled()) {
            return loginError("SR20004", "登录失败！该账号已被禁用");
        }

        // 成功之后
        return loginSuccess(hitUser);
    }

    public User hitUser(String mobile) {
        // 通过账号查询用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("mobile", mobile);
        return baseMapper.selectOne(userQueryWrapper);
    }

    public RS loginError(String code, String message) {
        return RS.error().code(code).message(message);
    }

    public RS loginSuccess(User hitUser) {
        String jwtToken = JwtUtils.getJwtToken(hitUser.getId(), hitUser.getNickname());

        return RS.success().data("token", jwtToken).data("userInfos", hitUser);
    }

    // 注册功能
    @Override
    public RS register(RegisterVo registerVo) {

        // 获取用户信息
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        String phoneCode = registerVo.getPhoneCode();

        // 非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(phoneCode)) {
            throw new CustomException("SR20002", "注册失败！手机号/密码/验证码不能为空！");
        }

        //获取redis验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if(!phoneCode.equals(redisCode)) {
            throw new CustomException("OW20011", "注册失败！验证码不能正确！");
        }

        // 如果有电话号码，这不能在注册
        User hitUser = hitUser(mobile);
        if(hitUser == null) {
            User user = new User();
            user.setMobile(mobile);
            user.setNickname(nickname);
            user.setPassword(MD5.encrypt(password));
            baseMapper.insert(user);
            // 注册成功,直接登陆
            hitUser = hitUser(mobile);
            assert false; // 断言hitUser 不能为空
            return loginSuccess(hitUser);
        } else {
            throw new CustomException("OW20012", "注册失败！该手机号码已经被注册，请直接登陆！");
        }





    }

    @Override
    public UserInfoVo getUserInfo(HttpServletRequest request) {

        String userId = JwtUtils.getUserIdByJwtToken(request);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        User user = baseMapper.selectOne(queryWrapper);
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(user, userInfoVo);
        return userInfoVo;
    }

    @Override
    public RS logout() {
        return RS.success().data("token", null);
    }
}
