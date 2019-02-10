const BASE_URL = "http://localhost:8080";
$( document ).ready(function() {
   const name =  $("#hidden_name").val();
   const role =  $("#hidden_role").val();
   const password =  $("#hidden_password").val();
   const email =  $("#hidden_email").val();
   localStorage.setItem("name", name);
   localStorage.setItem("email", email);
   localStorage.setItem("password", password);
   localStorage.setItem("role", role);
   
   $("#addQuestion").find("#email").val(localStorage.getItem("email"));
   $("#addQuestion").find("#password").val(localStorage.getItem("password"));
   
   $("#profile").show();
   $("#forum").hide();
   
   $("#sidebar-profile").on("click", (e) => {
	   e.preventDefault();
	   $("#profile").show();
	   $("#forum").hide();
	   if(!$("#sidebar-profile").hasClass("active")) $("#sidebar-profile").addClass("active");
	   while($("#sidebar-forum").hasClass("active")) $("#sidebar-forum").removeClass("active");
   });
   
   $("#sidebar-forum").on("click", (e) => {
	  e.preventDefault();
	  $("#profile").hide();
	  $("#forum").show();
	  while($("#sidebar-profile").hasClass("active")) $("#sidebar-profile").removeClass("active");
	  if(!$("#sidebar-forum").hasClass("active")) $("#sidebar-forum").addClass("active");
   });
   
   $("#addQuestion").find("#email").val(email);
   $("#addQuestion").find("#password").val(password);
   
   /* Get the Answer for the qid*/
   function fetchAnswer(qid){
	   $.ajax({
		   type: 'POST',
		   url: BASE_URL + '/dashboard',
		   data: {
			   qid: qid,
			   email: localStorage.getItem("email"),
			   password: localStorage.getItem("password"),
			   purpose: "fetchAnswer"
		   }
	   })
	   .done((result) => {
		   console.log(result);
	   })
	   .fail(err => {
		   console.log(err);
		});
   }
   
   
   /* Display all the Questions */
   $.ajax({
		type: 'POST',
	    url: BASE_URL + '/dashboard',
	    data: {
	    	email: localStorage.getItem("email"),
	    	password: localStorage.getItem("password"),
	    	purpose: "fetchQuestionList"
	    }
	})
	.done((result) => {
		console.log(result);
		result.forEach((q) => {
			const start = '<div class="question-item"><div class="title not-border" onclick="toggleBox(this)"><div class="arrow"></div>';
			const ques = '<div class="title-text">' + q.question + '</div><div class="question-credential">'+ q.userEmail +' | ' + q.createdAt.toLocaleString() + '</div></div>';
			const end = '</div>';
			$.ajax({
				   type: 'POST',
				   url: BASE_URL + '/dashboard',
				   data: {
					   qid: q.qid,
					   email: localStorage.getItem("email"),
					   password: localStorage.getItem("password"),
					   purpose: "fetchAnswer"
				   }
			   })
			   .done((result) => {
				   console.log(result[0]);
				   let answerContent, answerCredential = "";
				   if(result[0].aid>0 && result[0]){
					   answerContent = result[0].answer;
					   answerCredential = '<div class="answer-credential">Contributed By: ' + result[0].writer + ' | ' + result[0].writtenAt + '<div>';  
				   } else {
					   answerContent = "No Answers written";
				   }
				   const content = '<div class="content"><p>'+ answerContent +'</p>'+ answerCredential +'</div>';
				   const qitem = start + ques + content + end;
				   $(".questions-list").append(qitem);
			   })
			   .fail(err => {
				   console.log(err);
				});
		});
	})
	.fail(err => {
	  console.log(err);
	});
   // '<div class="content"><p> b maxime dolor voluptatem earum asperiores, enim quo.</p></div>';
   
   
});