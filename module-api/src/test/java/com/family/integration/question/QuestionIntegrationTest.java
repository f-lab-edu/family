package com.family.integration.question;

import com.family.application.question.QuestionFacade;
import com.family.entity.question.Question;
import com.family.interfaces.question.QuestionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class QuestionIntegrationTest {
    private MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private QuestionFacade questionFacade;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context).build();
    }

    @DisplayName("질문을 등록한다")
    @Test
    void registerQuestion() throws Exception {
        // given
        QuestionDto.RegisterRequest request = QuestionDto.RegisterRequest.builder()
                .content("content")
                .questionType(Question.QuestionType.FAMILY)
                .build();

        questionFacade.registerQuestion(request.toCommand());

        // when
        ResultActions actions = mockMvc.perform(
                post("/api/v1/questions")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        actions
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.questionToken").exists());
    }

    @DisplayName("질문을 조회한다")
    @Test
    void retrieveQuestion() throws Exception {
        // given
        QuestionDto.RegisterRequest request = QuestionDto.RegisterRequest.builder()
                .content("content")
                .questionType(Question.QuestionType.FAMILY)
                .build();

        questionFacade.registerQuestion(request.toCommand());

        // when
        ResultActions actions = mockMvc.perform(
                get("/api/v1/questions/{questionToken}", "token")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        actions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").exists());
    }
}
