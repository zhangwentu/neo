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
public class SyncOrderBodyDTO {

    /**
     * 通知ID
     * 是否必填：Y
     */
    @JSONField(name = "id")
    String id;

    /**
     * 通知创建时间
     * 通知创建的时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示北京时间2015年05月20日13点29分35秒。
     * 示例值：2015-05-20T13:29:35+08:00
     * 是否必填：Y
     */
    @JSONField(name = "create_time")
    String createTime;

    /**
     * 通知的类型，支付成功通知的类型为TRANSACTION.SUCCESS
     * 是否必填：Y
     */
    @JSONField(name = "event_type")
    String eventType;

    /**
     * 通知的资源数据类型，支付成功通知为encrypt-resource
     * 是否必填：Y
     */
    @JSONField(name = "resource_type")
    String resourceType;


    /**
     * 通知资源数据
     * 是否必填：Y
     */
    @JSONField(name = "resource")
    SyncOrderResourceDTO resource;

    /**
     * 回调摘要
     * 示例值：支付成功
     * 是否必填：Y
     */
    @JSONField(name = "summary")
    String summary;

}
