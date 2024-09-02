package com.family.entity.question;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
public class Question {
    @Id
    private Long id;

    private String questionToken;

    private String content;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @Getter
    @RequiredArgsConstructor
    public enum QuestionType {
        FAMILY("가족"),
        MEMBER("구성원");

        private final String value;
    }

    @Builder
    public Question(Long id, String questionToken, String content, QuestionType questionType) {
        this.id = id;
        this.questionToken = questionToken;
        this.content = content;
        this.questionType = questionType;
    }

    public Question() {}

}
