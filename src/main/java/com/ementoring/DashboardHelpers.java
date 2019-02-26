package com.ementoring;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

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
		res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    res.getWriter().write(json);
	}
	
	public void addAnswer(HttpServletRequest req, HttpServletResponse res, String email) throws ServletException, IOException, SQLException {
		String answer = req.getParameter("answer");
		String qid = req.getParameter("qid");
		String writerEmail = email;
		System.out.println("answer: " + answer + " qid: " + qid + " writerEmail: " + writerEmail );
		db.saveAnswer(answer, Integer.parseInt(qid), writerEmail);
		req.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(req, res);
	}
	
	public void viewAllAnswer(HttpServletRequest req, HttpServletResponse res, String email) throws ServletException, IOException, SQLException {
		String qid = req.getParameter("qid");
		ArrayList<AnswerQuestion> ansList = db.getAllAnswerQuestion(Integer.parseInt(qid));
		if(ansList.isEmpty()) {
			AnswerQuestion aq = new AnswerQuestion(0, "", "", 0, 0, "", "", "", "");
			ansList.add(aq);
		} 
		String json = new Gson().toJson(ansList);
		res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    res.getWriter().write(json);
	}
	
	public void getAllPeople(HttpServletRequest req, HttpServletResponse res, String email) throws ServletException, IOException, SQLException {
		ArrayList<User> userList = db.getAllPeople(email);
		if(userList.isEmpty()) {
			User u = new User("", "", "", "");
			userList.add(u);
		} 
		String json = new Gson().toJson(userList);
		res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    res.getWriter().write(json);
	}
	
	public void updateUserDesc(HttpServletRequest req, HttpServletResponse res, String email) throws ServletException, IOException, SQLException {
		String desc = req.getParameter("userDescription");
		db.updateUserDesc(desc, email);
		req.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(req, res);
	}
	
	public void connectPeople(HttpServletRequest req, HttpServletResponse res, String email) throws ServletException, IOException, SQLException {
		String receiverEmail = req.getParameter("sentTo");
		db.connectPeople(email, receiverEmail);
		req.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(req, res);
	}
	
	public void seeConnections(HttpServletRequest req, HttpServletResponse res, String email) throws ServletException, IOException, SQLException {
		String findRole = req.getParameter("findRole");
		ArrayList<User> userList = db.getAllConnections(email, findRole);
		if(userList.isEmpty()) {
			User u = new User("", "", "", "");
			userList.add(u);
		} 
		String json = new Gson().toJson(userList);
		res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    res.getWriter().write(json);
	}
	
	public void getAllRequests(HttpServletRequest req, HttpServletResponse res, String email) throws ServletException, IOException, SQLException {
		ArrayList<User> userList = db.getAllRequests(email);
		if(userList.isEmpty()) {
			User u = new User("", "", "", "");
			userList.add(u);
		} 
		String json = new Gson().toJson(userList);
		res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    res.getWriter().write(json);
	}
	
	public void manageRequest(HttpServletRequest req, HttpServletResponse res, String email) throws ServletException, IOException, SQLException {
		String toApprove = req.getParameter("toApprove");
		String action = req.getParameter("action");
		db.managePeople(toApprove, email, action);
		req.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(req, res);
	}
	
	public void addMessage(HttpServletRequest req, HttpServletResponse res, String email) throws ServletException, IOException, SQLException {
		String sentTo = req.getParameter("sendTo");
		String message = req.getParameter("message");
		db.addMessage(email, sentTo, message);
		req.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(req, res);
	}
	
	public void getAllMessages(HttpServletRequest req, HttpServletResponse res, String email) throws ServletException, IOException, SQLException {
		String sentBy = req.getParameter("sentBy");
		ArrayList<Messages> messages = db.getAllMessages(email, sentBy);
		if(messages.isEmpty()) {
			Messages m = new Messages(-1, "", "", "");
			messages.add(m);
		} 
		String json = new Gson().toJson(messages);
		res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    res.getWriter().write(json);
	}
	
	public void getAllUnis(HttpServletRequest req, HttpServletResponse res, String email) throws ServletException, IOException, SQLException {
		ArrayList<Universities> unis = db.getAllUnis();
		if(unis.isEmpty()) {
			Universities u = new Universities(-1, "");
			unis.add(u);
		} 
		String json = new Gson().toJson(unis);
		res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    res.getWriter().write(json);
	}
	
	public void referUni(HttpServletRequest req, HttpServletResponse res, String email) throws ServletException, IOException, SQLException {
		System.out.println("Referring universities");
		int uid = Integer.parseInt(req.getParameter("uid"));
		String referTo = req.getParameter("referTo");
		String referByName = req.getParameter("referByName");
		db.referUni(uid, referTo, email, referByName);
		req.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(req, res);
	}
	
	public void getAllRefers(HttpServletRequest req, HttpServletResponse res, String email) throws ServletException, IOException, SQLException {
		ArrayList<Refers> refers = db.getAllRefers(email);
		if(refers.isEmpty()) {
			Refers r = new Refers(-1, "", "", "", "");
			refers.add(r);
		} 
		String json = new Gson().toJson(refers);
		res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    res.getWriter().write(json);
	}
	
}
