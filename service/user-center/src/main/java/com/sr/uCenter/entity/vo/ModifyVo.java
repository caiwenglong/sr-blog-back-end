package com.sr.uCenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ModifyVo {
    @ApiModelProperty(value = "电话号码")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String phoneCode;
}
