package com.family.interfaces.question;

import com.family.domain.question.QuestionCommand;
import com.family.domain.question.QuestionInfo;
import com.family.entity.question.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class QuestionDto {
    @Getter
    @NoArgsConstructor
    public static class RegisterRequest {
        private String content;
        private Question.QuestionType questionType;

        public QuestionCommand.RegisterQuestion toCommand() {
            return QuestionCommand.RegisterQuestion.builder()
                    .content(content)
                    .questionType(questionType)
                    .build();
        }

        @Builder
        public RegisterRequest(String content, Question.QuestionType questionType) {
            this.content = content;
            this.questionType = questionType;
        }
    }

    @Getter
    public static class RetrieveResponse {
        private String content;

        public RetrieveResponse(QuestionInfo questionInfo) {
            this.content = questionInfo.getContent();
        }
    }

    @Getter
    public static class RegisterResponse {
        private String questionToken;
        private String content;

        public RegisterResponse(QuestionInfo questionInfo) {
            this.questionToken = questionInfo.getQuestionToken();
            this.content = questionInfo.getContent();
        }
    }

    @Getter
    public static class RetrieveListResponse{
        private List<QuestionInfo> questions;

        public RetrieveListResponse(List<QuestionInfo> questions) {
            this.questions = questions;
        }
    }
}
