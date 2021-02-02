package com.example.demo.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.QuestionAndAnswerModel;
import com.example.demo.model.QuizModel;

@RestController
@RequestMapping("quizapp")
public class QuizController {

	@Autowired
	public RestTemplate  restTemplate;
	
	public QuizModel quiz;

	public Integer value = 0;
	
	public Integer correctAnswerCount = 0;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getQuiz(HttpServletRequest servletRequest, ModelMap modelMap) throws URISyntaxException {
		String nextquiz =  servletRequest.getParameter("nextquiz");
		String checkanswer =  servletRequest.getParameter("checkanswer");
		
		if(checkanswer != null) {
			String choosenAnswer =  servletRequest.getParameter("answerValue");
			QuestionAndAnswerModel question = quiz.getResults()[value-1];
			if(question.getCorrect_answer().equals(choosenAnswer)) {
				modelMap.addAttribute("result", "Correct Answer");
			} else {
				modelMap.addAttribute("result", "Wrong Answer");
			}
			modelMap.addAttribute("answerValue", choosenAnswer);
			modelMap.put("result", quiz.getResults()[value-1]);
			modelMap.put("value", value-1);
			return new ModelAndView("quizapp/quiz");
		}
		
		if(nextquiz != null) {
			quiz = null;
			value = 0;
			correctAnswerCount = 0;
		}
		
		if(quiz != null) {
			String choosenAnswer =  servletRequest.getParameter("answerValue");
			validateQuiz(choosenAnswer);
		}
		
		return setData(modelMap);
	}

	private ModelAndView setData(ModelMap modelMap) throws URISyntaxException {
		if(value == 0) {
			URI uri = new URI("https://opentdb.com/api.php?amount=10&category=18&type=multiple");
			quiz  = restTemplate.getForObject(uri, QuizModel.class);
		} 
		
		if(value == 10) {
			modelMap.put("correctAnswerCount", correctAnswerCount);
			return new ModelAndView("quizapp/result");
		} else {
			modelMap.put("result", quiz.getResults()[value]);
			modelMap.put("value", value);
			value++;
		}
		return new ModelAndView("quizapp/quiz");
	}

	private void validateQuiz(String answer) {
		QuestionAndAnswerModel question = quiz.getResults()[value-1];
		if(question.getCorrect_answer().equals(answer)) {
			correctAnswerCount++;
		}
	}
}
