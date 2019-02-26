package com.ementoring;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(urlPatterns="/dashboard")
public class Dashboard extends HttpServlet {
	
	public String BASE_URL = "http://localhost:8080";
	private UserValidationService service = new UserValidationService();
	private DBconnect db = new DBconnect();
	protected DashboardHelpers helper = new DashboardHelpers();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String purpose = req.getParameter("purpose");
		try {
			if(service.userFound(email, password)) {
				try {
					ArrayList<User> user = db.getUserInfo(email);
					if(user.size()>0) {
						req.setAttribute("name", user.get(0).getName());
						req.setAttribute("email", user.get(0).getEmail());
						req.setAttribute("password", user.get(0).getPassword());
						req.setAttribute("role", user.get(0).getRole());
						req.setAttribute("description", user.get(0).getDesc());
						if(purpose.equals("login")) {
							helper.login(req, res);
						} else if(purpose.equals("userDesc")) {
							helper.updateUserDesc(req, res, email);
						} else if(purpose.equals("addQuestion")) {
							helper.addQuestion(req, res, email);
						} else if(purpose.equals("fetchQuestionList")) {
							helper.fetchQuestionList(req, res);
						} else if(purpose.equals("fetchAnswer")) {
							int QID = Integer.parseInt(req.getParameter("qid"));
							helper.fetchAnswer(req, res, QID);
						} else if(purpose.equals("addAnswer")) {
							helper.addAnswer(req, res, email);
						} else if(purpose.equals("allAnswerQuestion")) {
							helper.viewAllAnswer(req, res, email);
						} else if(purpose.equals("findPeople")) {
							helper.getAllPeople(req, res, email);
						} else if(purpose.equals("connectPeople")) {
							helper.connectPeople(req, res, email);
						} else if(purpose.equals("seeConnections")) {
							helper.seeConnections(req, res, email);
						} else if(purpose.equals("getAllRequests")) {
							helper.getAllRequests(req, res, email);
						} else if(purpose.equals("manageRequest")) {
							helper.manageRequest(req, res, email);
						} else if(purpose.equals("addMessage")) {
							helper.addMessage(req, res, email);
						} else if(purpose.equals("viewAllMessages")) {
							helper.getAllMessages(req, res, email);
						} else if(purpose.equals("getAllUnis")) {
							helper.getAllUnis(req, res, email);
						} else if(purpose.equals("referUni")) {
							helper.referUni(req, res, email);
						} else if(purpose.equals("getAllRefers")) {
							helper.getAllRefers(req, res, email);
						}
					} else {
						res.sendRedirect( BASE_URL + "/login");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				res.sendRedirect( BASE_URL + "/login");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	}
	
	
}
