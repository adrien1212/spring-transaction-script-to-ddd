package fr.adriencaubel.tstoddd.infrastructure.db;

import fr.adriencaubel.tstoddd.domain.model.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
