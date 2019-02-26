<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>E-Mentoring Portal | Register</title>
<jsp:include page="./lib/head.jsp" />
<!-- Custom css files -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">

</head>
<body>
	<header class="header_top">
		<h1>E-Mentoring</h1>
		<ul class="header_items">
			<li>Welcome</li>
		</ul>
	</header>
	<br>
	<main class="main-content">
		<div class="jumbotron register-box"> 
			<h2 class="text-center">Sign Up | E-Mentoring Portal</h2><hr><br>
				<form action="/register" method="post">
					<div class="form-group">
                        <label for="name" class="text-center">Name</label>
                        <input type="text" placeholder="Enter Your Name" name="name" id="name" required></input>
                    </div>
                    <div class="form-group">
                        <label for="email" class="text-center">E-Mail</label>
                        <input type="email" placeholder="Enter Your E-Mail" name="email" id="email" required></input>
                    </div>
                    <div class="form-group">
                        <label for="password" class="text-center">Password</label>
                        <input type="password" placeholder="Enter Password" name="password" id="password" required></input>
                    </div>
                    <div class="role-selector">
                    	<label for="role" class="text-center role-option">Role</label>
                    	<div class="form-check form-check-inline role-option">
						  <input class="form-check-input" type="radio" id="inlineCheckbox1" value="student" name="role">
						  <label class="form-check-label" for="inlineCheckbox1">Student</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" id="inlineCheckbox2" value="Mentor" name="role">
						  <label class="form-check-label" for="inlineCheckbox2">Mentor</label>
						</div>
                    </div>
                    
                   	<div class="text-center"><input type="submit" class="btn btn-primary" value="Sign Up"/></div>
                 	<small id="loginHelp" class="form-text text-center text-muted">Already have an account? <a href="/login">Sign in here</a></small>
				</form>
			</div>
	</main>
	<jsp:include page="./lib/js.jsp" />
	<!-- Custom js files -->		
	<script>
		var role = $("input[name='role']:checked").val();
		console.log(role);
	</script>
	</body>
</html>