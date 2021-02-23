package dev.huhao.example.articleservice.service;

import dev.huhao.example.articleservice.model.Article;
import dev.huhao.example.articleservice.repository.ArticleRepository;
import dev.huhao.example.articleservice.service.exception.ArticleNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article getArticle(String slug) {
        return articleRepository.findById(slug).orElseThrow(() -> new ArticleNotFoundException(slug));
    }
}
