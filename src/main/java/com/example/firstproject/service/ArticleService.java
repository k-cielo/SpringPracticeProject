package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Service //서비스객체선언
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository; //게시글 리파지터리 객체 주입

    public List<Article> index() {
        return articleRepository.findAll();//데이터는 리파지터리를 통해 가져오고 DB에서 조회된 결과를 반환함
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity(); //dto ->엔티티로 변환한 후 article에 저장
        if (article.getId() != null){
            return null;
        }
        return articleRepository.save(article);//article을 DB에 저장
    }

    public Article update(Long id, ArticleForm dto) {
        //1. DTO ->엔티티 변환하기
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        //2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);

        //3. 잘못된 요청 처리하기
        if (target == null || id!=article.getId()){
            //400 , 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return  null; //응답은 컨트롤러가 하므로 여기서는 null 반환
        }

        //4. 업데이트 및 정상 응답(200)하기
        target.patch(article);//기존데이터에 새 데이터 붙이기
        Article updated = articleRepository.save(target);//수정내용을 DB에 저장
        return updated;//응답은 컨트롤러가 하니까 여기서는 수정 데이터만 반환
    }

    public Article delete(Long id) {

        //1.대상찾기
        Article target = articleRepository.findById(id).orElse(null);

        //2.잘못된 요청 처리하기
        if (target == null) {
            return null;
        }

        //3.대상 삭제하기
        articleRepository.delete(target);
        return null;
    }
}
