package com.family.domain.question;

import com.family.entity.question.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class QuestionServiceImpl implements QuestionService {
    private HashMap<String, Question> questionMap = new HashMap<>();

    @Override
    public QuestionInfo registerQuestion(QuestionCommand.RegisterQuestion command) {
        String questionToken = "questionToken";
        Question initQuestion = command.toEntity(questionToken);
        questionMap.put("token", initQuestion);
        return new QuestionInfo(initQuestion);
    }

    @Override
    public QuestionInfo retrieveQuestion(String questionToken) {
        Question question = questionMap.get(questionToken);
        return new QuestionInfo(question);
    }

    @Override
    public List<QuestionInfo> retrieveAllQuestions() {
        List<Question> questionList = new ArrayList<>(questionMap.values());
        List<QuestionInfo> questionInfoList = questionList.stream()
                .map(QuestionInfo::new)
                .toList();
        return questionInfoList;
    }
}
