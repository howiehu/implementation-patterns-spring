package dev.huhao.example.realworld.articleservice.repository;

import dev.huhao.example.realworld.articleservice.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
}
