package dev.huhao.example.realworld.articleservice.service.exception;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String slug) {
        super(String.format("cannot find the article with slug %s", slug));
    }
}
