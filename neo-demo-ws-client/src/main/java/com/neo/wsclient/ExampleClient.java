package com.neo.wsclient;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        ExampleClient client = new ExampleClient(new URI("wss://ws.okx.com:8443/ws/v5/public"));
        // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
        client.setProxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 1080)));
        if (client.connectBlocking(10, TimeUnit.SECONDS)) {
            System.out.println("建立websocket连接");
//            client.send("{\"op\":\"subscribe\",\"args\":[{\"channel\":\"books5\",\"instId\":\"BTC-USDT\"}," +
//                    "{\"channel\":\"books5\",\"instId\":\"ETH-USDT\"}]}\n");
            client.send("{\"op\":\"subscribe\",\"args\":[{\"channel\":\"books5\",\"instId\":\"LTC-USDT\"}]}");
            while (client.isOpen()) {
                System.out.println("ping");
                client.sendPing();
                Thread.sleep(15000);

            }
        } else {
            System.err.println("Could not connect to the server.");
            return;
        }
    }
}
