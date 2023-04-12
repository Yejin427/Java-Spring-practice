package com.example.practice2.dto;

import com.example.practice2.entity.Article;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;
    public Article toEntity() {
        return new Article(id, title, content);
    }
}
