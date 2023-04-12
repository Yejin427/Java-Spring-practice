package com.example.practice2.repository;

import com.example.practice2.entity.Article;
import com.example.practice2.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CommentRepositoryTest {
    @Autowired CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {

        {
            Long articleId = 4L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            Article article = new Article(4L, "인생 영화는?", "제곧내");
            Comment a = new Comment(1L, article, "Cho", "너의 이름은");
            Comment b = new Comment(2L, article, "Kim", "메이즈러너");
            Comment c = new Comment(3L, article, "Park", "겨울왕국");
            List<Comment> expected = Arrays.asList(a, b, c);
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글 출력");
        }
        {
            Long articleId = 1L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            Article article = new Article(1L, "aaaa", "1111");
            List<Comment> expected = Arrays.asList();
            assertEquals(expected.toString(), comments.toString(), "1번 글 댓글 없음");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        {
            String nickname = "Park";
            List<Comment> comments = commentRepository.findByNickname(nickname);
            Comment a = new Comment(3L, new Article(4L, "인생 영화는?", "제곧내"), nickname, "겨울왕국");
            Comment b = new Comment(6L, new Article(5L, "소울 푸드는?", "댓 ㄱ"), nickname, "햄버거");
            Comment c = new Comment(9L, new Article(6L, "취미는?", "댓글"), nickname, "그림");
            List<Comment> expected = Arrays.asList(a, b, c);
            assertEquals(expected.toString(), comments.toString(), "Park 의 모든 댓글 출력!");
        }
    }
}