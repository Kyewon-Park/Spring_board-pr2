package kye.won.web.article.basic;

import kye.won.domain.Article;
import kye.won.domain.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/articles")
@RequiredArgsConstructor
public class BasicArticleController {
    private final ArticleRepository articleRepository;

    //글 목록
    @GetMapping
    public String articles(Model model){
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "basic/articles";
    }

    //게시글
    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, Model model){
        Article found = articleRepository.findById(articleId);
        model.addAttribute("article", found);
        return "basic/article";
    }

    //게시글 추가 폼
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }
    //게시글 추가 내용 등록
    @PostMapping("/add")
    public String add(@ModelAttribute Article article, RedirectAttributes redirectAttributes){
        Article saved = articleRepository.save(article);
        redirectAttributes.addAttribute("articleId", saved.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/articles/{articleId}";
    }


    //게시글 수정 폼
    @GetMapping("/{articleId}/edit")
    public String editForm(@PathVariable Long articleId, Model model){
        Article found = articleRepository.findById(articleId);
        model.addAttribute("article", found);
        return "basic/editForm";
    }
    //게시글 수정 내용 등록
    @PostMapping("/{articleId}/edit")
    public String edit(@PathVariable Long articleId, @ModelAttribute Article article){
        articleRepository.updateArticle(articleId, article);
        return "redirect:/basic/articles/{articleId}";
    }

    //테스트 위해 객체 임시 등록
    @PostConstruct
    public void init(){
        articleRepository.save(new Article("Coding good", "Lorem Ipsum is simply dummy text of the printing"));
        articleRepository.save(new Article("Coding good2", "Lorem Ipsum is simply dummy text of the printing"));
    }
}
