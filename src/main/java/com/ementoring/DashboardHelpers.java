package com.ementoring;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class DashboardHelpers {
	
	public String BASE_URL = "http://localhost:8080";
	private UserValidationService service = new UserValidationService();
	private DBconnect db = new DBconnect();
	
	public void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(req, res);
	}
	
	public void addQuestion(HttpServletRequest req, HttpServletResponse res, String email) throws ServletException, IOException, SQLException {
		String question = req.getParameter("question");
		Question q = new Question(question, email);
		db.saveQuestion(q);
		req.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(req, res);
	}
	
	public void fetchQuestionList(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
		ArrayList<Question> ques = db.getQuestionList();
		for(Question q: ques) {
			ArrayList<User> questionOwner = db.getUserInfo(q.getOwnerEmail());
			if(questionOwner.size() > 0) {
				q.setOwnerEmailToName(questionOwner.get(0).getName().trim());
			} else {
				throw new Error("Some Error Occured");
			}
		}
		String json = new Gson().toJson(ques);
		res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    res.getWriter().write(json);
	}
	
	public void fetchAnswer(HttpServletRequest req, HttpServletResponse res, int QID) throws SQLException, IOException {
		ArrayList<Answer> ans = db.getAnswer(QID);
		if(ans.isEmpty()) {
			Answer a = new Answer(0, 0, "", "", "");
			ans.add(a);
		} 
		
		String json = new Gson().toJson(ans);
		System.out.println(json);
		res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    res.getWriter().write(json);
	}

}
