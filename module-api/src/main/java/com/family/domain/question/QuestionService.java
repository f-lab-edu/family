package com.family.domain.question;


import java.util.List;

public interface QuestionService {
    QuestionInfo registerQuestion(QuestionCommand.RegisterQuestion command);
    QuestionInfo retrieveQuestion(String questionToken);
    List<QuestionInfo> retrieveAllQuestions();
}
