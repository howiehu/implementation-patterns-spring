package dev.huhao.example.realworld.articleservice.service;

import com.github.slugify.Slugify;
import dev.huhao.example.articleservice.service.exception.ArticleExistedException;
import dev.huhao.example.realworld.articleservice.model.Article;
import dev.huhao.example.realworld.articleservice.repository.ArticleRepository;
import dev.huhao.example.realworld.articleservice.service.exception.ArticleNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public Article createArticle(String title, String description, String body, UUID authorId) {
        var slug = new Slugify().slugify(title);

        if (articleRepository.existsById(slug)) {
            throw new ArticleExistedException(slug);
        }

        var article = new Article();
        article.setSlug(slug);
        article.setTitle(title);
        article.setDescription(description);
        article.setBody(body);
        article.setAuthorId(authorId);

        return articleRepository.save(article);
    }

    public Article getArticle(String slug) {
        return articleRepository.findById(slug).orElseThrow(() -> new ArticleNotFoundException(slug));
    }
}
