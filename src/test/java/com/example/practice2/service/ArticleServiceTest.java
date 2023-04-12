package com.example.practice2.service;

import com.example.practice2.dto.ArticleForm;
import com.example.practice2.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        //expected
        Article a = new Article(1L, "aaaa", "1111");
        Article b = new Article(2L, "bbbb", "2222");
        Article c = new Article(3L, "cccc", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        //real
        List<Article> articles = articleService.index();
        //비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_success() {   //exists id
        Long id = 1L;
        Article expected = new Article(id, "aaaa", "1111");
        Article article = articleService.show(id);
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void show_failed(){
        Long id = -1L;
        Article expected = null;
        Article article = articleService.show(id);
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_success() { //title, content dto 입력
        String title = "dddd";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);
        Article article = articleService.create(dto);
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void create_failed() {  //id 포함 dto 입력
        String title = "dddd";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;
        Article article = articleService.create(dto);
        assertEquals(expected, article);
    }
}