package dev.huhao.example.realworld.articleservice.protocol.controller;

import dev.huhao.example.realworld.articleservice.model.Article;
import dev.huhao.example.realworld.articleservice.service.ArticleService;
import dev.huhao.example.realworld.articleservice.service.exception.ArticleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/articles", produces = APPLICATION_JSON_VALUE)
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/{slug}")
    public Article get(@PathVariable String slug) {
        try {
            return articleService.getArticle(slug);
        } catch (ArticleNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
