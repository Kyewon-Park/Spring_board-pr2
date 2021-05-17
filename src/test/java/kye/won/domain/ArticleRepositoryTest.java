package kye.won.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ArticleRepositoryTest {

    ArticleRepository articleRepository = new ArticleRepository();
    private Logger log = LoggerFactory.getLogger(getClass());

    @AfterEach
    void afterEach(){
        articleRepository.clearStore();
    }

    @Test
    void save() {
        Article art = new Article("Coding good", "Lorem Ipsum is simply dummy text of the printing");
        Article saved = articleRepository.save(art);
        log.info(String.valueOf(art.getId()));
        Article found = articleRepository.findById(art.getId());
        assertThat(found).isEqualTo(saved);
    }

    @Test
    void findAll() {
        Article art1 = new Article("Coding good1", "Lorem Ipsum is simply dummy text of the printing");
        Article art2 = new Article("Coding good2", "Lorem Ipsum is simply dummy text of the printing");
        articleRepository.save(art1);
        articleRepository.save(art2);
        List<Article> articles = articleRepository.findAll();
        assertThat(articles.size()).isEqualTo(2);
        assertThat(articles).contains(art1,art2);
    }

    @Test
    void updateArticle() {
        Article art = new Article("Coding good", "Lorem Ipsum is simply dummy text of the printing");
        Article saved = articleRepository.save(art);
        Article newArt = new Article("Coding well", "Lorem Ipsum is simply dummy text of the printing");

        articleRepository.updateArticle(art.getId(), newArt);
        String updatedArticleName = articleRepository.findById(art.getId()).getArticleName();

        assertThat(updatedArticleName).isEqualTo(newArt.getArticleName());
    }
}