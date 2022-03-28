package com.neo.ws.client;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import org.tio.websocket.client.WebSocket;
import org.tio.websocket.client.WsClient;
import org.tio.websocket.client.config.WsClientConfig;
import org.tio.websocket.common.WsPacket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author neo
 * @date 2022/3/25 11:39
 */
public class ClientDemo {
    public static void main(String[] args) throws Exception {
        Map<Integer, Boolean> sent = new ConcurrentHashMap<>();
        int total = 10;
        Subject<Object> complete = PublishSubject.create().toSerialized();
        complete
                .buffer(total)
                .subscribe(
                        x -> {
                            Boolean all = sent.values().stream().reduce(true, (p, c) -> p && c);
                            if (all) {
                                System.out.println("All sent success! ");
                            }
                        });
        WsClient echo =
                WsClient.create(
                        "ws://127.0.0.1:9326",
                        new WsClientConfig(
                                e -> System.out.println("opened"),
                                e -> {
                                    WsPacket data = e.data;
                                    System.out.println(data.getWsBodyText());
                                    int i = 1;
                                    System.out.println("recv: " + i);
                                    complete.onNext(i);
                                },
                                e -> System.out.printf("on close: %d, %s, %s\n", e.code, e.reason, e.wasClean),
                                e -> System.out.println(String.format("on error: %s", e.msg)),
                                Throwable::printStackTrace));
        WebSocket ws = echo.connect();
        for (int i = 0; i < total; i++) {
            ws.send("" + i);
            sent.put(i, false);
            System.out.println("sent: " + i);
        }
    }
}
