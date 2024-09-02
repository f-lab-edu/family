package com.family.interfaces.question;

import com.family.base.ControllerTestSupport;
import com.family.domain.question.QuestionCommand;
import com.family.domain.question.QuestionInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;

import com.family.entity.question.Question;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuestionApiControllerTest extends ControllerTestSupport {

    @DisplayName("Dummy 데이터로 질문을 저장한다.")
    @Test
    void registerQuestion() throws Exception {
        // given
        QuestionDto.RegisterRequest request = QuestionDto.RegisterRequest.builder()
                .content("content")
                .questionType(Question.QuestionType.FAMILY)
                .build();

        Question question = Question.builder().questionToken("token").content("content").build();
        QuestionInfo mockQuestionInfo = new QuestionInfo(question);

        // when
        when(questionFacade.registerQuestion(any(QuestionCommand.RegisterQuestion.class))).thenReturn(mockQuestionInfo);

        // then
        mockMvc.perform(
                        post("/api/v1/questions")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.questionToken").value("token"));
    }

    @DisplayName("Dummy 데이터 질문을 조회한다.")
    @Test
    void retrieveQuestion() throws Exception {
        // given
        String questionToken = "token";

        Question question = Question.builder().questionToken("token").content("content").build();
        QuestionInfo mockQuestionInfo = new QuestionInfo(question);

        // when
        when(questionFacade.retrieveQuestion(any(String.class))).thenReturn(mockQuestionInfo);


        // then
        mockMvc.perform(
                get("/api/v1/questions/" + questionToken)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content").value("content"));
    }

    @DisplayName("Dummy 데이터 질문리스트를 조회한다.")
    @Test
    void retrieveAllQuestions() throws Exception {
        // given
        Question question = Question.builder().questionToken("token").content("content").build();
        QuestionInfo mockQuestionInfo = new QuestionInfo(question);
        List<QuestionInfo> mockQuestionInfoList = List.of(mockQuestionInfo, mockQuestionInfo);

        // when
        when(questionFacade.retrieveAllQuestions()).thenReturn(mockQuestionInfoList);

        // then
        mockMvc.perform(
                        get("/api/v1/questions")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questions").isArray());
    }
}
