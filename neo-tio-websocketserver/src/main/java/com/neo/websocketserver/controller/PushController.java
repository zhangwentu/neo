package com.neo.websocketserver.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tio.core.Tio;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.starter.TioWebSocketServerBootstrap;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/3/16 14:11
 */
@RestController
@RequestMapping("/push")
public class PushController {
    @Autowired
    private TioWebSocketServerBootstrap bootstrap;

    @GetMapping("/test")
    @ResponseBody
    public String pushMessage(String msg) {
        if (StrUtil.isEmpty(msg)) {
            msg = "hello tio websocket spring boot starter";
        }
        Tio.sendToAll(bootstrap.getServerTioConfig(), WsResponse.fromText(msg, "utf-8"));
        return "success";
    }
}
