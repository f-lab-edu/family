package com.family.application.question;

import com.family.domain.question.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionFacade {

    private final QuestionService questionService;


    public QuestionInfo registerQuestion(QuestionCommand.RegisterQuestion command) {
        QuestionInfo questionInfo = questionService.registerQuestion(command);
        return questionInfo;
    }

    public QuestionInfo retrieveQuestion(String questionToken) {
        QuestionInfo questionInfo = questionService.retrieveQuestion(questionToken);
        return questionInfo;
    }

    public List<QuestionInfo> retrieveAllQuestions() {
        List<QuestionInfo> questionInfoList = questionService.retrieveAllQuestions();
        return questionInfoList;
    }
}
