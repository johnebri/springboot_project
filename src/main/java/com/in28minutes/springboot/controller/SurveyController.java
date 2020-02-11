package com.in28minutes.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.model.Question;
import com.in28minutes.springboot.service.SurveyService;

@RestController
public class SurveyController {
	
	@Autowired
	private SurveyService surveyService;
	
	//  GET/survey/{surveyId}/questions
	@GetMapping("/surveys/{surveyId}/questions")
	public List<Question> retrieveQuestionForSurvey(@PathVariable String surveyId) {
		return surveyService.retrieveQuestions(surveyId);
	}
	
	//   GET /surveys/{surveyId}/questions/{questionId}
	@GetMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveDetailsForQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
		return surveyService.retrieveQuestion(surveyId, questionId);
	}
	
}
