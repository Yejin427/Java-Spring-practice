package com.example.practice2.entity;

import com.example.practice2.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne  //댓글 여러개, 하나 article
    @JoinColumn(name = "article_id")
    private Article article;
    @Column
    private String nickname;
    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        //예외 처리
        if(dto.getId() != null){
            throw new IllegalArgumentException("댓글 생성 실패! 댓글 id 없어야 함");
        }
        if(dto.getArticleId() != article.getId()){
            throw new IllegalArgumentException("댓글 생성 실패! 게시글 id 잘못됨");
        }
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        //exception
        if (this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id");
        //갱신
        if (dto.getNickname() != null) {
            this.nickname = dto.getNickname();
        }
        if (dto.getBody() != null) {
            this.body = dto.getBody();
        }
    }
}
