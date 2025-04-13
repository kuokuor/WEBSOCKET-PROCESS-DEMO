package com.kuokuor.demo.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 进度WebSocket处理器
 */
public class ProcessWebSocketHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    /**
     * 连接建立后，把session存起来
     *
     * @param session
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.put(session.getId(), session);
    }

    /**
     * 接收到消息后的处理
     *
     * @param session
     * @param message
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 处理客户端消息
        sessions.forEach((id, sessionItem) -> {
            try {
                if (sessionItem.isOpen()) {
                    sessionItem.sendMessage(new TextMessage("大伙们，" + session.getId() + "说：" + message.getPayload()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 连接断开后，删除session
     *
     * @param session
     * @param status
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session.getId());
    }

    /**
     * 发送进度更新给所有客户端
     * @param process
     */
    public static void sendProcess(int process) {
        sessions.forEach((id, session) -> {
            try {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(String.valueOf(process)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
