package com.sr.msm.service;

import java.util.Map;

public interface MsmService {

    // 获取短信验证码
    Boolean sendPhoneCode(Map<String, Object> param, String phoneNum);

}
