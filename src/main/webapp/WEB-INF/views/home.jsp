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
			
		</ul>
	</header>
	<br>
	<main class="main-content">
		<div class="text-center"> 
			<a href="/register"><button class="btn btn-secondary">Register</button></a>
		</div>
	</main>
	<jsp:include page="./lib/js.jsp" />
	<!-- Custom js files -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/home.js"></script>
	</body>
</html>