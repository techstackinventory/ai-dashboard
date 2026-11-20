
package com.learning.aidashboard.service;

import com.learning.aidashboard.dto.ChatApiResponse;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public ChatApiResponse generateResponse(String message) {

        long startTime = System.nanoTime();

        // 1. Execute the LLM request and retrieve full response
        ChatResponse aiResponse = chatClient
                .prompt()
                .user(message)
                .call()
                .chatResponse();

        // 2. Extract generated text
        String answer = aiResponse
                .getResult()
                .getOutput()
                .getText();

        // 3. Extract response metadata
        var metadata = aiResponse.getMetadata();

        String model = metadata.getModel();

        // 4. Extract token usage
        var usage = metadata.getUsage();

        Integer promptTokens = usage != null
                ? usage.getPromptTokens() : null;

        Integer completionTokens = usage != null
                ? usage.getCompletionTokens() : null;

        Integer totalTokens = usage != null
                ? usage.getTotalTokens() : null;

        // 5. Calculate response time
        long responseTimeMs =
                (System.nanoTime() - startTime) / 1_000_000;

        // 6. Return complete API response
        return new ChatApiResponse(
                answer,
                model,
                promptTokens,
                completionTokens,
                totalTokens,
                responseTimeMs
        );
    }
}
