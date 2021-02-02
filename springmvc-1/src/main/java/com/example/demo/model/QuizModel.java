package com.example.demo.model;

public class QuizModel {
	
	String response_code;
	
	QuestionAndAnswerModel[] results;

	public String getResponse_code() {
		return response_code;
	}

	public void setResponse_code(String response_code) {
		this.response_code = response_code;
	}

	public QuestionAndAnswerModel[] getResults() {
		return results;
	}

	public void setResults(QuestionAndAnswerModel[] results) {
		this.results = results;
	}
	
}
