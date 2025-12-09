package fr.adriencaubel.tstoddd.service;

import fr.adriencaubel.tstoddd.domain.Article;
import fr.adriencaubel.tstoddd.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article findById(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> new IllegalStateException("Article not found with id " + articleId));
    }
}
