package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService; // 댓글 서비스 객체 주입

    //1. 댓글 조회
    @GetMapping("/api/articles/{articleID}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){//comments()메서드 생성
        //서비스에 위임
        List<CommentDto> dtos = commentService.comments(articleId);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    //2. 댓글 생성
    @PostMapping("/api/articles/{articleId}/comment")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto){

        //서비스에 위임
        CommentDto createdDto = commentService.create(articleId. dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
    //3. 댓글 수정
    //4. 댓글 삭제


}
