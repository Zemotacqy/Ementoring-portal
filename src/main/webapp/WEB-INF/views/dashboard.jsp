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
			<li id="nav-logout"><button class="btn btn-danger">Logout</button></li>
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
				<li id="sidebar-inbox"><h6>Message Inbox</h6></li>
				<li id="sidebar-refers"><h6>Universities and Referrals</h6></li>
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
		
		<section id="inbox">
			<div class="inbox-people">
				<ul class="people-lists">
					<li><h4 class="colu1 text-center">S.No.</h4><h4 class="colu2">Name</h4><h4 class="colu3 text-center">Options</h4></li>
					<hr class="first-hr"/>
				</ul>
			</div>
			<!-- Add New Message Modal -->
				<div class="modal fade" id="addMessageModal" tabindex="-1" role="dialog" aria-labelledby="MessageModalLabel" aria-hidden="true">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="MessageModalLabel">Enter your new Message here:</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <form action="/dashboard" method="post" id="sendMessage">
					      <div class="modal-body">
								<div><label for="messageLabel" id="AddMessageLabel" class="text-center"></label></div>
								<div class="textarea-message"><textarea placeholder="Enter Your Message" rows="5" cols="50" name="message" id="message" required></textarea></div>
								<input type="hidden" name="email" id="email"></input>
								<input type="hidden" name="password" id="password"></input>
								<input type="hidden" name="purpose" id="purpose" value="addMessage"></input>
								<input type="hidden" name="sendTo" id="sendTo"></input>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
					        <input type="submit" class="btn btn-primary" value="Send Message"/>
					      </div>
				      </form>
				    </div>
				  </div>
				</div>
			
			<!-- View All Messages Modal -->
				<div class="modal fade" id="viewMessages" tabindex="-1" role="dialog" aria-labelledby="viewMessageModalLabel" aria-hidden="true">
				  <div class="modal-dialog modal-lg" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="viewMessageModalLabel">Messages</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
				      	<!-- Display all the messages here -->
				      	<div class="message-items">
				      		<div class="msg-item">
				      			<p>No Messages To Show</p>
				      		</div>
				      	</div>
				      </div>
				      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
					  </div>
				    </div>
				  </div>
				</div>
		</section>
		
		<section id="refers">
			<div class="refers-unis">
				<ul class="people-lists">
					<li><h6 class="colu1 text-center">S.No.</h6><h4 class="colu2">University Name</h4><h5 class="colu3 text-center">Recommendations</h5></li>
					<hr class="first-hr"/>
				</ul>
			</div>
			<!-- Refer university Modal -->
				<div class="modal fade" id="referUni" tabindex="-1" role="dialog" aria-labelledby="referUniLabel" aria-hidden="true">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="referUniLabel">Refer University</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <form action="/dashboard" method="post" id="referUniModal">
					      <div class="modal-body">
								<div><label for="messageLabel" id="AddMessageLabel" class="text-center">Enter Email Id (to the one you are referring)</label></div>
								<input class="form-control" type="email" name="referTo" id="referTo"></input>
								<input type="hidden" name="email" id="email"></input>
								<input type="hidden" name="password" id="password"></input>
								<input type="hidden" name="purpose" id="purpose" value="referUni"></input>
								<input type="hidden" name="referByName" id="referByName"></input>
								<input type="hidden" name="uid" id="uid"></input>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
					        <input type="submit" class="btn btn-primary" value="Refer University"/>
					      </div>
				      </form>
				    </div>
				  </div>
				</div>
			<div class="unis-list">
				<ul class="uni-lists">
					<li><h6 class="colu1 text-center">S.No.</h6><h4 class="colu2">University Name</h4></li>
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