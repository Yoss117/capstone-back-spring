package com.website.chat.controller;

import com.website.chat.service.ChatService;
import com.website.entity.ChatMessage;
import com.website.user.dto.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @PostMapping("/messages")
    public ResponseEntity<ChatMessage> sendChatMessage(@RequestBody ChatMessage message){

        //토큰에서 사용자 이름 추출
//        Long userCode = user.getUserCode();
//        message.setUserCode(userCode);

        ChatMessage msg = chatService.processAndGetAIResponse(message);

        return ResponseEntity.ok(msg);
    }
    // 사용자가 채팅 페이지에 진입했을 때 최근 채팅내역 20개를 가져와서 사용자에게 전달하는 API
    // 사용자 데이터 send 예시 : 사용자정보, 현재 페이지 수
    // 1페이지를 달라 : 최근 20개
    // 2페이지를 달라 : 최근 20개 건너뛰고 20개 (페이징)
    // 사용자가 스크롤을 올리면 그 뒤에 20개를 가져오는 식
    @GetMapping("/{chatRoomId}/messages")
    public ResponseEntity<List<ChatMessage>> getChatMessages(@PathVariable String chatRoomId){
        return ResponseEntity.ok(chatService.getMessages(chatRoomId));
    }
}
