package kye.won.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ArticleRepository {
    private static final HashMap<Long, Article> store = new HashMap<>();
    private static Long sequence = 0L;

    //저장, article 찾아오기, article 모두 찾아오기, 삭제
    public Article save(Article article){
        article.setId(++sequence);
        store.put(sequence, article);
        return article;
    }

    public Article findById(Long id){
        Article article = store.get(id);
        return article;
    }

    public List<Article> findAll(){
        return new ArrayList<>(store.values());
    }

    public void updateArticle(Long id, Article updateTo){
        Article found = findById(id);
        found.setArticleName(updateTo.getArticleName());
        found.setArticleBody(updateTo.getArticleBody());
    }

    public void clearStore(){
        store.clear();
    }
}
