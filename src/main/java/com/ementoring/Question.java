package com.ementoring;

import java.util.*;

public class Question {
	private String question;
	private String userEmail;
	
	public Question(String newQuestion, String newUserEmail) {
		question = newQuestion;
		userEmail = newUserEmail;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String getOwnerEmail() {
		return userEmail;
	}
	
}
