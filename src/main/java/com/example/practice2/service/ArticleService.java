package com.example.practice2.service;

import com.example.practice2.dto.ArticleForm;
import com.example.practice2.entity.Article;
import com.example.practice2.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service    //service 객체 스프링부트에 생성
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();
        Article target = articleRepository.findById(id).orElse(null);
        if(target == null || id != article.getId()){
            return null;
        }
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if(target == null){
            return null;
        }
        articleRepository.delete(target);
        return target;
    }
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto list -> entity list
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        //entity ->db
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        //강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결재 실패!")
        );
        return articleList;
    }

}
