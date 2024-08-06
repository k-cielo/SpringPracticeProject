package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {


    @Autowired
    private CommentRepository commentRepository; //댓글 리파지터리 객체 주입
    @Autowired
    private ArticleRepository articleRepository; //게시글 리파지터리 객체 주입

    public List<CommentDto> comments(Long articleId) {
        //1.댓글조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        //2.엔티티-> DTO변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i = 0; i < comments.size(); i++){ //1. 조회한 댓글 엔티티 수만큼 반복하기
            Comment c = comments.get(i);//2. 조회한 댓글 엔티티 하나씩 가져오기
            CommentDto dto = CommentDto.createCommentDto(c); // 변환한 DTO를 dtos 리스트에 삽입
            dtos.add(dto);
        }
        //3. 결과 반환
        return dtos;

    }
}
