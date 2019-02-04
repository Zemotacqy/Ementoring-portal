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
		res.setHeader("Access-Control-Allow-Origin", "*");
	    res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
	    res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String purpose;
		if(req.getParameter("purpose") != null) {
			purpose = req.getParameter("purpose");
		} else {
			purpose = "login";
		}
		System.out.println(purpose);
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
							System.out.println("regular login");
							req.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(req, res);
						} else {
							System.out.println("login not accepted");
							res.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
						    res.setCharacterEncoding("UTF-8"); // You want world domination, huh?
						    res.getWriter().write("allow");       // Write response body.
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
