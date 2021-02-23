package dev.huhao.example.articleservice.service.exception;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String slug) {
        super("cannot find the article with slug " + slug);
    }
}
