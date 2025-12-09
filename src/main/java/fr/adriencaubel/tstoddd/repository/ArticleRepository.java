package fr.adriencaubel.tstoddd.repository;

import fr.adriencaubel.tstoddd.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
