package com.family.domain.question;

import com.family.entity.question.Question;
import lombok.Builder;
import lombok.Getter;

public class QuestionCommand {

    @Getter
    @Builder
    public static class RegisterQuestion {
        private final String content;
        private final Question.QuestionType questionType;

        public Question toEntity(String questionToken) {
            return Question.builder()
                    .questionToken(questionToken)
                    .content(content)
                    .questionType(questionType)
                    .build();
        }
    }
}
