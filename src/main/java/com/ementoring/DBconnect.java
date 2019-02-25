package com.ementoring;

import java.sql.*;
import java.util.*;

public class DBconnect {
	
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private String dbuser = "selab";
	private String dbpassword = "ementoring";
	private String DBname = "ementoring";

	public DBconnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DBname, dbuser, dbpassword);
			System.out.println("Connected to DB" + DBname);
			st = con.createStatement();
		} catch(Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
	
	public ArrayList<User> getAllUsers() throws SQLException {
		ArrayList<User> userList = new ArrayList<>();
		try {
			String query = "SELECT * from Users";
			rs = st.executeQuery(query);
			System.out.println("Records from User table Fetched :");
			while(rs.next()) {
				User user = new User(rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("role"));
				user.setDesc(rs.getString("UserDesc"));
				userList.add(user);
			}
		} catch(Exception ex) {
			System.out.println("error: " + ex);
		} finally {
	        if(rs != null) rs.close();
	    }
		return userList;
	}
	
	public void updateUserDesc(String desc, String email) throws SQLException {
		String query = "UPDATE Users SET UserDesc = '"+ desc +"' WHERE email='" + email + "'";
		int status = st.executeUpdate(query);
		System.out.println("User Desc updated with status: " + status);
	}
	
	public void saveUser(User user) throws SQLException {
		String query = "INSERT INTO `Users` (`name`, `email`, `password`, `role`, `UserDesc`) VALUES ('" + user.getName() + "', '" + user.getEmail() + "', '" + user.getPassword() + "', '" + user.getRole() + "', '"+"')";
		int status = st.executeUpdate(query);
		System.out.println("User saved with status: " + status);
	}
	
	public ArrayList<User> getUserInfo(String email) throws SQLException {
		ArrayList<User> userList = new ArrayList<>();
		try {
			String query = "SELECT * from Users WHERE email = '" + email + "'";
			rs = st.executeQuery(query);
			System.out.println("Records from User table Fetched :");
			while(rs.next()) {
				User user = new User(rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("role"));
				user.setDesc(rs.getString("UserDesc")); 
				System.out.println(user.getDesc());
				userList.add(user);
			}
		} catch(Exception ex) {
			System.out.println("error: " + ex);
		} finally {
	        if(rs != null) rs.close();
	    }
		return userList;
	}
	
	public void saveQuestion(Question q) throws SQLException {
		java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		String query = "INSERT INTO Questions (userEmail, question, createdAt) VALUES ('" + q.getOwnerEmail() + "', '" + q.getQuestion() + "', '" + sqlDate + "')";
		int status = st.executeUpdate(query);
		System.out.println("New Question added to database with status " + status);
	}
	
	public ArrayList<Question> getQuestionList() throws SQLException {
		ArrayList<Question> quesList = new ArrayList<>();
		try {
			String query = "SELECT * from Questions";

			rs = st.executeQuery(query);
			System.out.println("Records from Question table Fetched :");
			while(rs.next()) {
				Question q = new Question(rs.getString("question"), rs.getString("userEmail"));
				q.setCreatedAt((rs.getString("createdAt").toString()));
				q.setQid(rs.getInt("qid"));
				quesList.add(q);
			}
		} catch(Exception ex) {
			System.out.println("error: " + ex);
		} finally {
	        if(rs != null) rs.close();
	    }
		return quesList;
	}
	
	public ArrayList<Answer> getAnswer(int QID) throws SQLException {
		ArrayList<Answer> ansList = new ArrayList<>();
		try {
			String query = "SELECT aid, answer, qid, writtenAt, name, writerEmail FROM Answers,Users WHERE qid = "+ QID +" AND email = Answers.writerEmail";
			rs = st.executeQuery(query);
			System.out.println("Records from Answer table fetched: ");
			if (!rs.isBeforeFirst() ) {    
			    return ansList;
			} 
			while(rs.next()) {
				Answer ans = new Answer(rs.getInt("aid"), rs.getInt("qid"), rs.getString("answer"), rs.getString("name"), rs.getString("writerEmail"));
				ans.setWrittenAt((rs.getString("writtenAt").toString()));
				ansList.add(ans);
			}
		} catch(Exception ex) {
			System.out.println("error: " + ex);
		} finally {
			if(rs != null) rs.close();
		}
		return ansList;
	}
	
	public void saveAnswer(String answer, int qid, String writerEmail) throws SQLException {
		java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		String query = "INSERT INTO Answers (writerEmail, qid, answer, writtenAt) VALUES ('"+ writerEmail +"', "+ qid +", '" + answer + "', '" + sqlDate + "')";
		int status = st.executeUpdate(query);
		System.out.println("New Answer added to database with status " + status);
	}
	
	public ArrayList<AnswerQuestion> getAllAnswerQuestion(int QID) throws SQLException {
		ArrayList<AnswerQuestion> ansQuesEl = new ArrayList<>();
		try {
			String query = "SELECT qid, askerEmail, question, createdAt, claps, aid, answer, writerEmail, writtenAt, name, role FROM (SELECT Questions.qid AS qid, userEmail AS askerEmail, question, createdAt, claps, aid, answer, writerEmail, writtenAt FROM Questions INNER JOIN Answers ON Questions.qid = Answers.qid AND Questions.qid = " + QID + ") AS QA INNER JOIN Users ON Users.email = QA.writerEmail";
			rs = st.executeQuery(query);
			System.out.println("Records from Answers and Questions table fetched: ");
			if (!rs.isBeforeFirst() ) {    
			    return ansQuesEl;
			} 
			while(rs.next()) {
				AnswerQuestion El = new AnswerQuestion(rs.getInt("qid"), rs.getString("askerEmail"), rs.getString("question"), rs.getInt("claps"), rs.getInt("aid"), rs.getString("answer"), rs.getString("writerEmail"), rs.getString("name"), rs.getString("role"));
				El.setWrittenAt((rs.getString("writtenAt").toString()));
				El.setCreatedAt((rs.getString("createdAt").toString()));
				ansQuesEl.add(El);
			}
		} catch(Exception ex) {
			System.out.println("error: " + ex);
		} finally {
			if(rs != null) rs.close();
		}
		return ansQuesEl;
	}
	
	public ArrayList<User> getAllPeople(String email) throws SQLException {
		ArrayList<User> users = new ArrayList<>();
		try {
			System.out.println(email);
			String query = "Select DISTINCT Users.email as email, name, role as userRole, UserDesc as userDesc from Users, (Select t1.email from (Select email from Users WHERE email != ALL (SELECT senderEmail from Connections where senderEmail='"+email+"' OR receiverEmail='"+email+"')) as t1, (Select email from Users WHERE email != ALL (SELECT receiverEmail from Connections where senderEmail='" + email + "' OR receiverEmail='"+ email +"')) as t2 where t1.email=t2.email) as ul where ul.email = Users.email";               
			rs = st.executeQuery(query);
			System.out.println("Records from Connections and Users table fetched: ");
			if (!rs.isBeforeFirst() ) {    
			    return users;
			} 
			while(rs.next()) {
				User user = new User(rs.getString("name"), rs.getString("email"), rs.getString("userDesc"), rs.getString("userRole"));
				users.add(user);
			}
		} catch(Exception ex) {
			System.out.println("error: " + ex);
		} finally {
			if(rs != null) rs.close();
		}
		return users;
	}
	
	public void connectPeople(String senderEmail, String receiverEmail) throws SQLException {
		try {
			String query = "INSERT INTO Connections (senderEmail, senderName, senderRole, senderDesc, receiverEmail, receiverName, receiverRole, receiverDesc, status) SELECT *, 'sent' FROM (Select email AS SenderEmail, name AS SenderName, role AS SenderRole, UserDesc AS senderDesc FROM Users Where email = '"+ senderEmail +"') as t1, (Select email AS receiverEmail, name AS receiverName, role AS receiverRole, UserDesc AS receiverDesc FROM Users Where email = '"+ receiverEmail +"') as t2";
			int status = st.executeUpdate(query);
			System.out.println("Connection made with status: " + status);
		} catch(Exception ex) {
			System.out.println("error: " +ex);
		}
	}
	
	public ArrayList<User> getAllConnections(String email, String conRole) throws SQLException {
		ArrayList<User> users = new ArrayList<>();
		try {
			System.out.println(email);
			String query = "Select receiverEmail as email, receiverName as name, receiverRole as role, receiverDesc as description from Connections where senderEmail='" + email + "' AND status='accepted' AND receiverRole='" + conRole + "' UNION ALL Select senderEmail as email, senderName as name, senderRole as role, senderDesc as description from Connections where receiverEmail='" + email + "' AND status='accepted' AND SenderRole='" + conRole + "';";               
			rs = st.executeQuery(query);
			System.out.println("Records from Connections Fetched and returned all Students: ");
			if (!rs.isBeforeFirst() ) {    
			    return users;
			} 
			while(rs.next()) {
				User user = new User(rs.getString("name"), rs.getString("email"), rs.getString("description"), rs.getString("role"));
				users.add(user);
			}
		} catch(Exception ex) {
			System.out.println("error: " + ex);
		} finally {
			if(rs != null) rs.close();
		}
		return users;
	}
	
	public ArrayList<User> getAllRequests(String email) throws SQLException {
		ArrayList<User> users = new ArrayList<>();
		try {
			String query = "SELECT senderEmail as email, senderName as name, senderRole as role, senderDesc as description from Connections where receiverEmail = '" + email + "' and status = 'sent'";               
			rs = st.executeQuery(query);
			System.out.println("Records from Connections Fetched All requests Presented: ");
			if (!rs.isBeforeFirst() ) {    
			    return users;
			} 
			while(rs.next()) {
				System.out.println(rs.getString("email"));
				User user = new User(rs.getString("name"), rs.getString("email"), rs.getString("description"), rs.getString("role"));
				users.add(user);
			}
		} catch(Exception ex) {
			System.out.println("error: " + ex);
		} finally {
			if(rs != null) rs.close();
		}
		return users;
	}
	
	public void managePeople(String sentBy, String receivedBy, String action) throws SQLException {
		try {
			String query = "update  Connections set status='" + action + "' where senderEmail='" + sentBy + "' and receiverEmail='" + receivedBy + "'";
			int status = st.executeUpdate(query);
			System.out.println("Connection managed made with status: " + status);
		} catch(Exception ex) {
			System.out.println("error: " +ex);
		}
	}
}


