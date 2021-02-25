package dev.huhao.example.realworld.articleservice.protocol.controller.request;

import java.util.UUID;

public class ArticleCreateRequest {
    public String title;
    public String description;
    public String body;
    public UUID authorId;
}
