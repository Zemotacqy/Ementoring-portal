package com.ementoring;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/register")
public class Register extends HttpServlet{
	public String BASE_URL = "http://localhost:8080";
	private UserValidationService service = new UserValidationService();
	DBconnect db = new DBconnect();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		int userType = Integer.parseInt(req.getParameter("userType"));
		
		try {
			if(service.userFound(email)) {
				req.setAttribute("success", true);
				req.setAttribute("msg", "User with the Email: " + email.toString() + " already exists!!");
				req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, res);
			} else {
				User user = new User(name, email, password, userType);
				db.saveUser(user);
				req.setAttribute("success", false);
				req.setAttribute("msg", "User is registered Successfully");
				req.setAttribute("name", user.getName());
				res.sendRedirect(BASE_URL + "/login");
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e);
			e.printStackTrace();
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
	}
	
}
