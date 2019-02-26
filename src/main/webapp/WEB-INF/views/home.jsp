<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>E-Mentoring Portal</title>
<jsp:include page="./lib/head.jsp" />
<!-- Custom css files -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">

</head>
<body>
	<header class="header_top">
		<h1>E-Mentoring</h1>
		<ul class="header_items">
			<li><a href="/login"><button class="btn btn-primary">Login</button></a></li>
			<li><a href="/register"><button class="btn btn-secondary">Register</button></a></li>
		</ul>
	</header>
	<br>
	<main class="main-content">
		<div> 
			<img src="/img/img1.jpg" class="img-first"/>
			<br /><br/>
			<div class="head-cont">
				<h3 class="head-line">Empower Women, Empower Family, Empower Nation</h3>
			</div>
			<br/><br/>
			<div class="feature-cont">
				<h2 class="text-center">Key Features of this Platform</h2><br />
				<div class="flex-feature">
					<div class="features">
						<ul>
							<li>Forum</li>
							<li>Connect with Mentors and Friends</li>
							<li>Login as Mentor or Student</li>
							<li>Get recommended by Mentors</li>
							<li>List of Universities</li>
						</ul>
					</div>
					<div class="feature-img">
						<img src="/img/img-2.jpg"/> 	
					</div>
				</div>
			</div>
		</div>
	</main>
	<footer>
		<p class="text-center">Coded By Zemotacqy</p>
	</footer>
	<jsp:include page="./lib/js.jsp" />
	<!-- Custom js files -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/home.js"></script>
	</body>
</html>