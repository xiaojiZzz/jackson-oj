package com.jackson.ojquestionservice.controller.inner;

import com.jackson.ojmodel.model.entity.Question;
import com.jackson.ojmodel.model.entity.QuestionSubmit;
import com.jackson.ojquestionservice.service.QuestionService;
import com.jackson.ojquestionservice.service.QuestionSubmitService;
import com.jackson.ojserviceclient.service.QuestionFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 该题目服务仅微服务内部调用，前端不调用
 *
 * @author jackson
 */
@RestController
@RequestMapping("/inner")
public class QuestionInnerController implements QuestionFeignClient {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @GetMapping("/get/id")
    @Override
    public Question getQuestionById(@RequestParam("questionId") long questionId) {
        return questionService.getById(questionId);
    }

    @GetMapping("/question_submit/get/id")
    @Override
    public QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") long questionSubmitId) {
        return questionSubmitService.getById(questionSubmitId);
    }

    @PostMapping("/question_submit/update")
    @Override
    public boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit) {
        return questionSubmitService.updateById(questionSubmit);
    }

    @PostMapping("/question/update")
    @Override
    public boolean updateQuestionAcceptedNumById(@RequestParam("questionId") long questionId) {
        return questionService.updateQuestionAcceptedNumById(questionId);
    }
}
