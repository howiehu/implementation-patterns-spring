package dev.huhao.example.realworld.articleservice.service.exception;

public class ArticleExistedException extends RuntimeException {
    public ArticleExistedException(String slug) {
        super(String.format("the article with slug %s already exists", slug));
    }
}
