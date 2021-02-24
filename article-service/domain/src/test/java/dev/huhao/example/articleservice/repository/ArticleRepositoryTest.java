package dev.huhao.example.articleservice.repository;

import dev.huhao.example.articleservice.model.Article;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleRepositoryTest extends RepositoryTestBase {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArticleRepository articleRepository;

    @Nested
    class findById {

        @Test
        void should_find_article_by_slug() {
            // Given
            Article existingArticle = new Article();
            existingArticle.setSlug("fake-title");
            existingArticle.setTitle("Fake Title");
            existingArticle.setDescription("Description");
            existingArticle.setBody("Something");
            existingArticle.setCreatedAt(Instant.now());
            existingArticle.setUpdatedAt(Instant.now());
            existingArticle.setAuthorId(UUID.randomUUID());

            entityManager.persist(existingArticle);
            entityManager.flush();

            // When
            var result = articleRepository.findById(existingArticle.getSlug());

            // Then
            assertThat(result)
                    .hasValueSatisfying(v -> assertThat(v).usingRecursiveComparison().isEqualTo(existingArticle));
        }

        @Test
        void should_be_empty_when_no_existing_data() {
            // When
            var result = articleRepository.findById("");

            // Then
            assertThat(result).isEmpty();
        }
    }
}
