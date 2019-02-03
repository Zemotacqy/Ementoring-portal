<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>E-Mentoring</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>
	<center>
		<h1>Welcome to E-Mentoring Platform</h1>
		<form action="/register" method="post">
		  <div>
		    <h1>Register</h1>
		    <p>Please fill in this form to create an account.</p>
		    <hr>
		    
		    <label><b>Name</b></label>
		    <input type="text" placeholder="Enter Email" name="name">
		
		    <label><b>Email</b></label>
		    <input type="text" placeholder="Enter Email" name="email" required>
		
		    <label><b>Password</b></label>
		    <input type="password" placeholder="Enter Password" name="password" required>
		
		    <label><b>User Type</b></label>
		    <input type="text" placeholder="Enter type of User" name="userType" required>
		    <hr>
		    
		    <button type="submit">Register</button>
		  </div>
		</form>
	</center>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/home.js"></script>
		
	</body>
</html>