package kye.won.web.article.basic;

import kye.won.domain.Article;
import kye.won.domain.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/articles")
@RequiredArgsConstructor
public class BasicArticleController {
    private final ArticleRepository articleRepository;

    @GetMapping
    public String articles(Model model){
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "basic/articles";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, Model model){
        Article found = articleRepository.findById(articleId);
        model.addAttribute("article", found);
        return "basic/article";
    }

    @GetMapping("/{articleId}/edit")
    public String articleEdit(@PathVariable Long articleId, Model model){
        Article found = articleRepository.findById(articleId);
        model.addAttribute("article", found);
        return "basic/editForm";
    }

    @PostMapping("/{articleId}/edit")
    public String edit(@PathVariable Long articleId, @ModelAttribute Article article){
        articleRepository.updateArticle(articleId, article);
        return "redirect:/basic/articles/{articleId}";
    }

    @PostConstruct
    public void init(){
        articleRepository.save(new Article("Coding good", "Lorem Ipsum is simply dummy text of the printing"));
        articleRepository.save(new Article("Coding good2", "Lorem Ipsum is simply dummy text of the printing"));
    }
}
