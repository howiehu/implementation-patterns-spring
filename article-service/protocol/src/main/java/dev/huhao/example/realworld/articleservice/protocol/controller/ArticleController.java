package dev.huhao.example.realworld.articleservice.protocol.controller;

import dev.huhao.example.realworld.articleservice.model.Article;
import dev.huhao.example.realworld.articleservice.protocol.controller.request.ArticleCreateRequest;
import dev.huhao.example.realworld.articleservice.service.ArticleService;
import dev.huhao.example.realworld.articleservice.service.exception.ArticleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping(value = "/articles", produces = APPLICATION_JSON_VALUE)
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody ArticleCreateRequest data, UriComponentsBuilder uriComponentsBuilder) {
        var article = articleService.createArticle(data.title, data.description, data.body, data.authorId);
        var uriComponents = uriComponentsBuilder.path("/articles/{slug}").buildAndExpand(article.getSlug());
        return created(uriComponents.toUri()).body(article);
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
