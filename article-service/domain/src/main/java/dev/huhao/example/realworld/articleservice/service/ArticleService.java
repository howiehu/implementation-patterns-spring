package dev.huhao.example.realworld.articleservice.service;

import com.github.slugify.Slugify;
import dev.huhao.example.realworld.articleservice.model.Article;
import dev.huhao.example.realworld.articleservice.repository.ArticleRepository;
import dev.huhao.example.realworld.articleservice.service.exception.ArticleNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article getArticle(String slug) {
        return articleRepository.findById(slug).orElseThrow(() -> new ArticleNotFoundException(slug));
    }

    public Article createArticle(String title, String description, String body, UUID authorId) {
        var article = new Article();
        article.setTitle(title);
        article.setDescription(description);
        article.setBody(body);
        article.setAuthorId(authorId);

        var slug = new Slugify().slugify(title);
        article.setSlug(slug);

        return articleRepository.save(article);
    }
}
