package com.neo.wechatpay.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * <p></p>
 *
 * @author neo
 * @date 2021/4/8 14:48
 */
@Data
public class PayResponseDTO {

    /**
     * 返回状态码
     * 错误码，SUCCESS为清算机构接收成功，其他错误码为失败。
     * 是否必填：Y
     */
    @JSONField(name = "code")
    String code;

    /**
     * 返回信息，成功或者错误原因。
     * 示例值：成功
     * 是否必填：Y
     */
    @JSONField(name = "message")
    String message;


}
