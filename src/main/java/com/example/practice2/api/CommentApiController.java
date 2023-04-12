package com.example.practice2.api;

import com.example.practice2.annotation.RunningTime;
import com.example.practice2.dto.CommentDto;
import com.example.practice2.entity.Comment;
import com.example.practice2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    //댓글 목록
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        List<CommentDto> dtos = commentService.comments(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    //댓글 작성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto){
        CommentDto createdDto = commentService.create(articleId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
    //댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto){
        CommentDto createdDto = commentService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
    //댓글 삭제
    @RunningTime
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        CommentDto createdDto = commentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
}
