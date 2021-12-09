package dev.huhao.example.realworld.articleservice.protocol.controller.request;

import java.util.UUID;

public record ArticleCreateRequest(String title, String description, String body, UUID authorId) {
}
