package com.family.domain.question;

import static org.assertj.core.api.Assertions.assertThat;

import com.family.entity.question.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionServiceTest {
    @Autowired
    private QuestionService questionService;

    @DisplayName("질문을 등록한 내용을 조회한다.")
    @Test
    void registerQuestion() {
        // given
        String token = "token";
        QuestionCommand.RegisterQuestion command = QuestionCommand.RegisterQuestion.builder()
                .questionType(Question.QuestionType.FAMILY)
                .content("content")
                .build();

        // when
        questionService.registerQuestion(command);
        QuestionInfo questionInfo = questionService.retrieveQuestion(token);

        // then
        assertThat(questionInfo.getQuestionType()).isEqualTo(command.getQuestionType());
        assertThat(questionInfo.getContent()).isEqualTo(command.getContent());
    }
}
