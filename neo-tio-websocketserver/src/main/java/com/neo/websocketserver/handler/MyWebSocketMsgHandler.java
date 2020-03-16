package com.neo.websocketserver.handler;

import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.server.handler.IWsMsgHandler;

/**
 * <p></p>
 *
 * @author neo
 * @date 2020/3/16 14:06
 */
@Slf4j
public class MyWebSocketMsgHandler implements IWsMsgHandler {
    @Override
    public HttpResponse handshake(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        return httpResponse;
    }

    @Override
    public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        log.info("[WS] 握手成功");
    }

    @Override
    public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        log.info("[WS] 接收到bytes消息");

        return null;
    }

    @Override
    public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {

        return null;
    }

    @Override
    public Object onText(WsRequest wsRequest, String s, ChannelContext channelContext) throws Exception {
        log.info("[WS] 接收到文本消息：{}", s);
        return null;
    }
}
