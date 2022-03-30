package com.neo.wsclient;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author neo
 * @date 2022/3/30 19:51
 */
public class ExampleClient extends WebSocketClient {
    public ExampleClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public ExampleClient(URI serverURI) {
        super(serverURI);
    }

    public ExampleClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send("ping");
        System.out.println("opened connection");
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The close codes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    public static void main(String[] args) throws URISyntaxException {
        ExampleClient client = new ExampleClient(new URI("wss://ws.okx.com:8443/ws/v5/public"));
        // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
        client.connect();
        while (!client.isOpen()) {
            System.out.println("还没有打开");
        }
        System.out.println("建立websocket连接");
        client.send("ping");
    }
}
