<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>E-Mentoring Portal | Dashboard</title>
<jsp:include page="./lib/head.jsp" />
<!-- Custom css files -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body>
	<input type="hidden" value="${name}" id="hidden_name"/>
	<input type="hidden" value="${password}" id="hidden_password"/>
	<input type="hidden" value="${email}" id="hidden_email"/>
	<input type="hidden" value="${role}" id="hidden_role"/>
	<header class="header_top">
		<h1>E-Mentoring</h1>
		<ul class="header_items">
			<li>Options</li>
		</ul>
	</header>
	<br>
	<main class="main-content">
		
	</main>
<jsp:include page="./lib/js.jsp" />
<!-- Custom js files -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dashboard.js"></script>
</body>
</html>