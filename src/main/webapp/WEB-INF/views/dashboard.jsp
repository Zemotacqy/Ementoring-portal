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
		<div class="sidebar">
			<ul class="sidebar-items">
				<li class="active" id="sidebar-profile"><h6>Profile</h6></li>
				<li id="sidebar-forum"><h6>Forum</h6></li>
			</ul>
		</div>
		
		<section id="profile">
			<div class="jumbotron profile-box text-center">
				<h2>Welcome ${name}</h2>
				<hr>
				<br>
				<div class="profile-item-group">
					<p>Email </p>
					<p>${email} </p>
				</div>
				<div class="profile-item-group">
					<p>Role </p>
					<p>${role} </p>
				</div>
			</div>
		</section>
		
		<section id="forum">
			<div class="ask-question text-center">
				<h4><strong>Ask a Question in the Forum</strong></h4>
				<div><img class="question-img" src="${pageContext.request.contextPath}/img/qmark.png" /></div>
				<br>
				<button class="btn btn-success" data-toggle="modal" data-target="#addQuestionModal" id="add-question">Add Question</button>
				<!-- Add Question Modal -->
				<div class="modal fade" id="addQuestionModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">New Question</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <form action="/dashboard" method="post" id="addQuestion">
					      <div class="modal-body">
								<div><label for="question" class="text-center">Type your Question here </label></div>
								<div class="textarea-question"><textarea placeholder="Enter Your question" rows="5" cols="50" name="question" id="question" required></textarea></div>
								<input type="hidden" name="email" id="email"></input>
								<input type="hidden" name="password" id="password"></input>
								<input type="hidden" name="purpose" id="purpose" value="addQuestion"></input>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
					        <input type="submit" class="btn btn-primary" value="Add Question"/>
					      </div>
				      </form>
				    </div>
				  </div>
				</div>
				
				
			</div>
			<div class="questions-list text-center">
			</div>
		</section>
	</main>
<jsp:include page="./lib/js.jsp" />
<!-- Custom js files -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dashboard.js"></script>
<script>
function toggleBox(questionSection, qid){
	   questionSection.classList.toggle('opened');
	   localStorage.setItem("selectedQuestion", qid);
}
</script>
</body>
</html>