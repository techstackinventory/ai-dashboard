package com.learning.aidashboard.controller;

import com.learning.aidashboard.dto.ChatApiRequest;
import com.learning.aidashboard.dto.ChatApiResponse;
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
    public ChatApiResponse chat(
            @RequestBody ChatApiRequest request) {

        return chatService.generateResponse(
                request.message()
        );
    }
}