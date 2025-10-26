package com.website.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

public class WebSocketEventListener {
    private static final Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);
//    private final SimpMessagingTemplate messagingTemplate;
//    private final ChatService chatService;


    @EventListener
    public void handleListener(SessionConnectedEvent event) { // 메서드 이름 유지
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        log.info(">>>> 1. 새로운 WebSocket 연결 감지됨: {}", headerAccessor.getSessionId());

        // --- 헤더 읽는 방식 변경 ---
    }

    @EventListener
    public void handleDisconnectListener(SessionDisconnectEvent event) { // 메서드 이름 유지
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        log.info("WebSocket 연결 해제됨: {}", headerAccessor.getSessionId());
    }
}
