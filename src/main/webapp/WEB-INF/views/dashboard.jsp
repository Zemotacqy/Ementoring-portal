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
	<input type="hidden" value="${description}" id="hidden_desc"/>
	<header class="header_top">
		<h1>E-Mentoring</h1>
		<ul class="header_items">
			<li id="nav-email"></li>
		</ul>
	</header>
	<br>
	<main class="main-content">
		<div class="sidebar">
			<ul class="sidebar-items">
				<li class="active" id="sidebar-profile"><h6>Profile</h6></li>
				<li id="sidebar-forum"><h6>Forum</h6></li>
				<li id="sidebar-connect"><h6>Connect</h6></li>
				<li id="sidebar-manage-connect"><h6>Manage Connections</h6></li>
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
				<div class="profile-item-group">
					<p class="user-desc-label">User Description </p>
					<div class="userDesc">
						<p class="user-desc-p">${description} </p>
						<button type="button" class="btn btn-success btn-sm">Edit</button>
					</div>
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
				<!-- Answer Modal -->
				<div class="modal fade" id="addAnswerModal" tabindex="-1" role="dialog" aria-labelledby="answerQuestion" aria-hidden="true">
					<div class="modal-dialog modal-lg" role="document">
					  <div class="modal-content">
					    <div class="modal-header">
					      <h5 class="modal-title" id="answerQuestion"></h5>
					      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					        <span aria-hidden="true">&times;</span>
					      </button>
					    </div>
					    <form action="/dashboard" method="post" id="addAnswer">
						      <div class="modal-body">
									<div><label for="question" class="text-center">Type your Answer here </label></div>
									<div class="textarea-question"><textarea placeholder="Enter Your question" rows="15" cols="75" name="answer" id="answer" required></textarea></div>
									<input type="hidden" name="email" id="email"></input>
									<input type="hidden" name="password" id="password"></input>
									<input type="hidden" name="purpose" id="purpose" value="addAnswer"></input>
									<input type="hidden" name="qid" id="qid"></input>
									<input type="hidden" name="writerEmail" id="writerEmail"></input>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
						        <input type="submit" class="btn btn-primary" value="Add Answer"/>
						      </div>
					    </form>
					  </div>
					</div>
				</div>
				<!-- ALL Answer Modal -->
				<div class="modal fade" id="allAnswerModal" tabindex="-1" role="dialog" aria-labelledby="allAnswerQuestion" aria-hidden="true">
					<div class="modal-dialog modal-lg" role="document">
					  <div class="modal-content">
					    <div class="modal-header">
					      <h5 class="modal-title" id="allAnswerQuestion"></h5>
					      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					        <span aria-hidden="true">&times;</span>
					      </button>
					    </div>
						<div class="modal-body">
						</div>
						<div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
						</div>
					    
					  </div>
					</div>
				</div>
			</div>
			<div class="questions-list text-center">
			</div>
		</section>
		
		<section id="connect">
			<div class="connect-intro text-center">
				<h3>Connect to Friends and Mentors</h3>
				<button class="btn btn-success" id="find-people">Find New People</button>
				<button class="btn btn-primary" id="see-connections">See Your Connections</button>
			</div>
			<div class="show-people">
				<ul class="people-lists">
					<li><h4 class="colu1 text-center">S.No.</h4><h4 class="colu2">Name</h4><h4 class="colu3 text-center">Status</h4></li>
					<hr class="first-hr"/>
				</ul>
			</div>
			<div class="show-connections">
				<ul class="con-tab text-center">
					<li id="con-tab-student">Students</li>
					<li id="con-tab-mentor">Mentors</li>
				</ul>
				<div class="show-people connection-tab">
					<ul class="people-lists">
						<li><h4 class="colu1 text-center">S.No.</h4><h4 class="colu2">Name</h4><h4 class="colu3 text-center">Status</h4></li>
						<hr class="first-hr"/>
					</ul>
				</div>
			</div>
		</section>
		
		<section id="manage-connect">
			<div class="manage-people">
				<ul class="people-lists">
					<li><h4 class="colu1 text-center">S.No.</h4><h4 class="colu2">Name</h4><h4 class="colu3 text-center">Action</h4></li>
					<hr class="first-hr"/>
				</ul>
			</div>
		</section>
	</main>
<jsp:include page="./lib/js.jsp" />
<!-- Custom js files -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dashboard.js"></script>
<script>
function toggleBox(questionSection){
	   questionSection.classList.toggle('opened');
}

function selectQuestion(buttonSelected, QID, Question) {
	   $("#answerQuestion").html(Question);
	   $("#addAnswer").find("#qid").val(QID);
	   $("#addAnswer").find("#email").val(localStorage.getItem("email"));
	   $("#addAnswer").find("#password").val(localStorage.getItem("password"));
}

function viewAllAnswer(viewAllEl, QID, Question) {
	$("#allAnswerQuestion").html(Question);
	$.ajax({
		type: 'POST',
		url: BASE_URL + '/dashboard',
		data: {
	    	email: localStorage.getItem("email"),
	    	password: localStorage.getItem("password"),
	    	purpose: "allAnswerQuestion",
	    	qid: QID
	    }
	})
	.done(function (result){
		console.log(result);
		$("#allAnswerQuestion").append();
		const parent = $("#allAnswerModal").find(".modal-body");
		result.forEach(function(El) {
			const answerCredential = '<p class="answerCredential">' + El.name +' ( '+  El.role +' ) '+ ' | ' + El.writtenAt +'</p>';
			const answerEl = '<div class="answerEl">'+ El.answer + answerCredential + '</div>';
			parent.append(answerEl);
		});
	})
	.fail(function (err){
		console.log(err);
	}); 
}
</script>
</body>
</html>