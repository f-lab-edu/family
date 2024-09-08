package com.family.application.question;

import static org.assertj.core.api.Assertions.assertThat;

import com.family.domain.question.QuestionCommand;
import com.family.domain.question.QuestionInfo;
import com.family.entity.question.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionFacadeTest {

    @Autowired
    private QuestionFacade questionFacade;

    @DisplayName("질문을 등록한 내용을 조회한다.")
    @Test
    void registerQuestion() {
        // given
        String token = "token";
        QuestionCommand.RegisterQuestion command = QuestionCommand.RegisterQuestion.builder()
                .content("content")
                .questionType(Question.QuestionType.FAMILY)
                .build();
        // when
        questionFacade.registerQuestion(command);
        QuestionInfo questionInfo = questionFacade.retrieveQuestion(token);

        // then
        assertThat(questionInfo.getContent()).isEqualTo(command.getContent());
        assertThat(questionInfo.getQuestionType()).isEqualTo(command.getQuestionType());
    }
}
