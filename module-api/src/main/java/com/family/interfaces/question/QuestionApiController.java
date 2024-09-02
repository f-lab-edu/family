package com.family.interfaces.question;

import com.family.application.question.QuestionFacade;
import com.family.domain.question.QuestionCommand;
import com.family.domain.question.QuestionInfo;
import com.family.interfaces.question.QuestionDto.RetrieveResponse;
import com.family.interfaces.question.QuestionDto.RegisterResponse;
import com.family.interfaces.question.QuestionDto.RegisterRequest;
import com.family.interfaces.question.QuestionDto.RetrieveListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
public class QuestionApiController {
    private final QuestionFacade questionFacade;

    @PostMapping
    public ResponseEntity<RegisterResponse> registerQuestion(@RequestBody RegisterRequest request) {
        QuestionCommand.RegisterQuestion command = request.toCommand();
        QuestionInfo questionInfo = questionFacade.registerQuestion(command);
        RegisterResponse response = new RegisterResponse(questionInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{questionToken}")
    public ResponseEntity<RetrieveResponse> retrieveQuestion(@PathVariable("questionToken") String questionToken) {
        QuestionInfo questionInfo = questionFacade.retrieveQuestion(questionToken);
        RetrieveResponse response = new RetrieveResponse(questionInfo);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<RetrieveListResponse> retrieveAllQuestions() {
        List<QuestionInfo> questionInfoList = questionFacade.retrieveAllQuestions();
        RetrieveListResponse response = new RetrieveListResponse(questionInfoList);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
