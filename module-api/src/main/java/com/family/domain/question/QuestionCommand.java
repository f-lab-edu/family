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

        public Question toEntity() {
            return Question.builder()
                    .content(content)
                    .questionType(questionType)
                    .build();
        }
    }
}
