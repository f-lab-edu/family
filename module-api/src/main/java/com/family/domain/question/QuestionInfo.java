package com.family.domain.question;

import com.family.entity.question.Question;
import lombok.Builder;
import lombok.Getter;

@Getter
public class QuestionInfo {
    private final String questionToken;
    private final String content;
    private final Question.QuestionType questionType;

    @Builder
    public QuestionInfo(Question question) {
        this.questionToken = question.getQuestionToken();
        this.content = question.getContent();
        this.questionType = question.getQuestionType();
    }

}
