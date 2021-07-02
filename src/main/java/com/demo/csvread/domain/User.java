package com.demo.csvread.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;

    private Long totalScoreByReporties;

    private Long devScore;

    private Long qaScore;

    private String managerName;

}
