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
public class SyncOrderResourceDTO {

    /**
     * 加密算法类型
     * 对开启结果数据进行加密的加密算法，目前只支持AEAD_AES_256_GCM
     * 是否必填：Y
     */
    @JSONField(name = "algorithm")
    String algorithm;

    /**
     * 数据密文
     * Base64编码后的开启/停用结果数据密文
     * 是否必填：Y
     */
    @JSONField(name = "ciphertext")
    String cipherText;

    /**
     * 附加数据
     * 是否必填：N
     */
    @JSONField(name = "associated_data")
    String associatedData;

    /**
     * 原始类型
     * 原始回调类型，为transaction
     * 是否必填：Y
     */
    @JSONField(name = "original_type")
    String originalType;


    /**
     * 回调摘要
     * 示例值：支付成功
     * 是否必填：Y
     */
    @JSONField(name = "nonce")
    String nonce;

}
