package com.learning.aidashboard.dto;

public record ChatApiResponse(

        String response,

        String model,

        Integer promptTokens,

        Integer completionTokens,

        Integer totalTokens,

        long responseTimeMs

) {
}