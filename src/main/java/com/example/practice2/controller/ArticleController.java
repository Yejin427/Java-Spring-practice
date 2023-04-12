package com.example.practice2.controller;

import com.example.practice2.dto.ArticleForm;
import com.example.practice2.dto.CommentDto;
import com.example.practice2.entity.Article;
import com.example.practice2.repository.ArticleRepository;
import com.example.practice2.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j  //로깅
public class ArticleController {
    @Autowired  //스프링 부트가 생성한 객체 자동 연결
    private ArticleRepository articleRepositry;
    @Autowired
    private CommentService commentService;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());
        //dto => entity
        Article article = form.toEntity();
        //repository에게 entity를 db에 저장하게함
        Article saved = articleRepositry.save(article);

        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        //id로 데이터 가져옴
        Article articleEntity = articleRepositry.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);
        //데이터 모델에 등록
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);
        //보여줄 페이지
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        List<Article> articleEntityList = articleRepositry.findAll();
        model.addAttribute("articleList", articleEntityList);
        return "articles/index";
    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Article articleEntity = articleRepositry.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        Article articleEntity = form.toEntity();
        Article target = articleRepositry.findById(articleEntity.getId()).orElse(null);
        if(target != null){
            articleRepositry.save(articleEntity);   //entity 가 db로 갱신
        }
        return "redirect:/articles/"+articleEntity.getId();
    }
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Article target = articleRepositry.findById(id).orElse(null);
        if(target != null){
            articleRepositry.delete(target);
            rttr.addFlashAttribute("msg", "Deleted!");
        }
        return "redirect:/articles";
    }
}
