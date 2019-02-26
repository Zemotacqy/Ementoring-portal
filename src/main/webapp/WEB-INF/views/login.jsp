<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>E-Mentoring Portal | Login</title>
<jsp:include page="./lib/head.jsp" />
<!-- Custom css files -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">

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
		
			<div class="jumbotron login-box"> 
				<h2 class="text-center">Login to E-Mentoring Portal</h2>
				<hr>
				<br>
				<form action="/dashboard" method="post">
					<div class="form-group">
                        <label for="email" class="text-center">E-Mail</label>
                        <input type="email" placeholder="Enter E-mail" name="email" id="email" required></input>
                    </div>
                    <div class="form-group">
                        <label for="password" class="text-center">Password</label>
                        <input type="password" placeholder="Enter Password" name="password" id="password" required></input>
                    </div>
                    <input type="hidden" name="purpose" id="purpose" value="login"></input>
                   	<div class="text-center"><input type="submit" class="btn btn-primary" value="Sign In"/></div>
                 	<small id="loginHelp" class="form-text text-center text-muted">New to E-Mentoring platform? <a href="/register">Sign up here</a></small>
				</form>
			</div>
	
		
	</main>
	<jsp:include page="./lib/js.jsp" />
	<!-- Custom js files -->
</body>
</html>