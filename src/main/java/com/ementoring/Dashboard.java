package com.ementoring;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/dashboard")
public class Dashboard extends HttpServlet {
	
	public String BASE_URL = "http://localhost:8080";
	private UserValidationService service = new UserValidationService();
	private DBconnect db = new DBconnect();
	
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
						if(purpose.equals("login")) {
							req.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(req, res);
						} else if(purpose.equals("addQuestion")) {
							String question = req.getParameter("question");
							Question q = new Question(question, email);
							db.saveQuestion(q);
							req.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(req, res);
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
