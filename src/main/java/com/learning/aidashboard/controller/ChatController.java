package com.learning.aidashboard.controller;

import com.learning.aidashboard.dto.ChatRequest;
import com.learning.aidashboard.dto.ChatResponse;
import com.learning.aidashboard.service.ChatService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {

        String answer = chatService.generateResponse(
                request.message()
        );

        return new ChatResponse(answer);
    }
}