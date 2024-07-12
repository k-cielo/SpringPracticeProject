package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor //생성자 대체 어노테이션
@NoArgsConstructor// 기본 생성자 추가 어노테이션
@ToString//toString()메서드 대체 어노테이션
@Entity
@Getter //게터추가
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//DB가 id 자동생성
    private Long id;

    @Column
    private String title;

    @Column
    private String content;


}
