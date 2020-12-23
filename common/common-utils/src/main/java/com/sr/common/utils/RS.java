package com.sr.common.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class RS {

    @ApiModelProperty(value = "是否成功")
    private Boolean isSuccess;

    @ApiModelProperty(value = "返回的code")
    private String code;

    @ApiModelProperty(value = "信息")
    private String message;

    @ApiModelProperty(value = "返回的data")
    private Map<String, Object> data = new HashMap<>();


    private RS(){}

    // 成功时返回的对象
    public static com.sr.common.utils.RS success() {
        com.sr.common.utils.RS rs = new com.sr.common.utils.RS();
        rs.setIsSuccess(true);
        rs.setCode(com.sr.common.utils.ResultCode.SUCCESS_CODE);
        rs.setMessage("成功");
        return rs;
    }

    // 失败时返回的对象
    public static com.sr.common.utils.RS error() {
        com.sr.common.utils.RS rs = new com.sr.common.utils.RS();
        rs.setIsSuccess(false);
        rs.setCode(com.sr.common.utils.ResultCode.ERROR_CODE);
        rs.setMessage("失败");
        return rs;
    }

    // 以下三个是可以修改的属性

    public com.sr.common.utils.RS message(String message) {
        this.setMessage(message);
        return this;
    }

    public com.sr.common.utils.RS code(String code) {
        this.setCode(code);
        return this;
    }

    public com.sr.common.utils.RS data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public com.sr.common.utils.RS data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }


}
