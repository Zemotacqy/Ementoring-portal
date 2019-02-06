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
			const qitem = '<div class="question-item"><div class="title not-border" onclick="toggleBox(this)"><div class="arrow"></div><div class="title-text">' + q.question + '</div><div class="question-credential">'+ q.userEmail +' | ' + q.createdAt.toLocaleString() + '</div></div><div class="content"><p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Repellendus eum tempora sunt beatae, voluptatibus, iste vero excepturi aspernatur tenetur pariatur qui quaerat dolorum maxime dolor voluptatem earum asperiores, enim quo.</p></div></div>';
			$(".questions-list").append(qitem);
		});
	})
	.fail(err => {
	  console.log(err);
	});
   
   
});