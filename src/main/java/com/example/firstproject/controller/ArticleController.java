package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Slf4j //로깅기능을 위한 어노테이션 추가
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {

        log.info(form.toString());//로깅코드추가
//        System.out.println(form.toString());  기존코드 주석처리
        //1.DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());//로깅코드추가
//        System.out.println(article.toString());


        //2.리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
//        System.out.println(saved.toString());
        return "redirect:/articles/"+saved.getId();//리다이렉트

    }

    @GetMapping("/articles/{id}")//데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model){//매개변수 id로 받아오기
        log.info("id = "+id);//id를 잘 받았는지 확인하는 로그 찍기

        //1. id를 조회해 데이터 가져오기
//        Optional<Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);//값이 있으면 artic~변수에 넣고 없으면 null저장

        //2. 모델에서 데이터 등록하기
        model.addAttribute("article", articleEntity);

        //3. 뷰 페이지 설정하기
        return "articles/show";//목록으로 돌아가기 링크를 넣을 뷰 확인
    }

    @GetMapping("/articles")
    public String index(Model model){

        //1.모든 데이터 가져오기
//        List<Article> articleEntityList =(List<Article>) articleRepository.findAll(); Iterable을 List로 다운캐스팅해 문제해결
//        Interable<Article> articleEntityList = articleRepository.findAll();  List<Articel>을 Iterable<Article>로 업캐스팅
        List<Article> articleEntityList = articleRepository.findAll();

        //2.모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);

        //3.뷰 페이지 설정하기

        return "articles/index";
    }
}
