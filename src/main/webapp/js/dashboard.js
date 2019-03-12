const BASE_URL = "http://localhost:8080";
$( document ).ready(function() {
   const name =  $("#hidden_name").val();
   const role =  $("#hidden_role").val();
   const password =  $("#hidden_password").val();
   const email =  $("#hidden_email").val();
   const description = $("#hidden_desc").val();
   
   localStorage.setItem("name", name);
   localStorage.setItem("email", email);
   localStorage.setItem("password", password);
   localStorage.setItem("role", role);
   localStorage.setItem("description", description);
   
   // set the nav email
   $("#nav-email").html("Welcome <strong>" + localStorage.getItem("name") + "</strong>");
   
   $("#nav-logout").on('click', (e) => {
	  e.preventDefault();
	  localStorage = null;
	  window.location.href="http://localhost:8080/";
   });
   
   $("#addQuestion").find("#email").val(localStorage.getItem("email"));
   $("#addQuestion").find("#password").val(localStorage.getItem("password"));
   
   $("#sendMessage").find("#email").val(localStorage.getItem("email"));
   $("#sendMessage").find("#password").val(localStorage.getItem("password"));
 
   $("#profile").show();
   $("#forum").hide();
   $("#connect").hide();
   $("#manage-connect").hide();
   $("#inbox").hide();
   $("#refers").hide();
   
   /* Logic for Edit user Description */
   $(".userDesc").find("button").on('click', (e) => {
	   e.preventDefault();
	   const writeDesc = '<form method="post" action="/dashboard"><input name="userDescription" type="textarea" rows="3" cols="5"/><input type="hidden" name="purpose" id="purpose" value="userDesc"><input type="hidden" name="email" value="'+ localStorage.getItem("email") +'" id="email"></input><input type="hidden" value="'+ localStorage.getItem("password") +'" name="password" id="password"></input></input><div class="action-userDesc"><input id="cancel-userDesc" type="button" value="cancel" class="btn btn-secondary btn-sm"/><input type="submit" value="Submit" class="btn btn-primary btn-sm"/></div></form>';
	   $(".userDesc").html(writeDesc);
	   $("#cancel-userDesc").on("click", (e) => {
		   e.preventDefault();
		   const cancelUserDesc = '<p class="user-desc-p">' + description + '</p><button type="button" class="btn btn-success btn-sm">Edit</button>';
		   $(".userDesc").html(cancelUserDesc);
	   });
   });
   
   
   
   $("#sidebar-profile").on("click", (e) => {
	   e.preventDefault();
	   $("#profile").show();
	   $("#forum").hide();
	   $("#connect").hide();
	   $("#manage-connect").hide();
	   $("#inbox").hide();
	   $("#refers").hide();
	   if(!$("#sidebar-profile").hasClass("active")) $("#sidebar-profile").addClass("active");
	   while($("#sidebar-forum").hasClass("active")) $("#sidebar-forum").removeClass("active");
	   while($("#sidebar-connect").hasClass("active")) $("#sidebar-connect").removeClass("active");
	   while($("#sidebar-inbox").hasClass("active")) $("#sidebar-inbox").removeClass("active");
	   while($("#sidebar-manage-connect").hasClass("active")) $("#sidebar-manage-connect").removeClass("active");
	   while($("#sidebar-refers").hasClass("active")) $("#sidebar-refers").removeClass("active");
   });
   
   $("#sidebar-forum").on("click", (e) => {
	  e.preventDefault();
	  $("#profile").hide();
	  $("#forum").show();
	  $("#connect").hide();
	  $("#manage-connect").hide();
	  $("#inbox").hide();
	  $("#refers").hide();
	  while($("#sidebar-profile").hasClass("active")) $("#sidebar-profile").removeClass("active");
	  while($("#sidebar-connect").hasClass("active")) $("#sidebar-connect").removeClass("active");
	  while($("#sidebar-manage-connect").hasClass("active")) $("#sidebar-manage-connect").removeClass("active");
	  while($("#sidebar-inbox").hasClass("active")) $("#sidebar-inbox").removeClass("active");
	  if(!$("#sidebar-forum").hasClass("active")) $("#sidebar-forum").addClass("active");
	  while($("#sidebar-refers").hasClass("active")) $("#sidebar-refers").removeClass("active");
   });
   
   $("#sidebar-connect").on("click", (e) => {
	  e.preventDefault();
	  $("#profile").hide();
	  $("#forum").hide();
	  $("#connect").show();
	  $("#inbox").hide();
	  $("#refers").hide();
	  $("#manage-connect").hide();
	  while($("#sidebar-profile").hasClass("active")) $("#sidebar-profile").removeClass("active");
	  while($("#sidebar-forum").hasClass("active")) $("#sidebar-forum").removeClass("active");
	  while($("#sidebar-inbox").hasClass("active")) $("#sidebar-inbox").removeClass("active");
	  while($("#sidebar-manage-connect").hasClass("active")) $("#sidebar-manage-connect").removeClass("active");
	  if(!$("#sidebar-connect").hasClass("active")) $("#sidebar-connect").addClass("active");
	  while($("#sidebar-refers").hasClass("active")) $("#sidebar-refers").removeClass("active");
   });
   
   $("#sidebar-inbox").on("click", (e) => {
		  e.preventDefault();
		  $("#profile").hide();
		  $("#forum").hide();
		  $("#connect").hide();
		  $("#inbox").show();
		  $("#refers").hide();
		  $("#manage-connect").hide();
		  while($("#sidebar-profile").hasClass("active")) $("#sidebar-profile").removeClass("active");
		  while($("#sidebar-forum").hasClass("active")) $("#sidebar-forum").removeClass("active");
		  while($("#sidebar-manage-connect").hasClass("active")) $("#sidebar-manage-connect").removeClass("active");
		  while($("#sidebar-connect").hasClass("active")) $("#sidebar-connect").removeClass("active");
		  if(!$("#sidebar-inbox").hasClass("active")) $("#sidebar-inbox").addClass("active");
		  while($("#sidebar-refers").hasClass("active")) $("#sidebar-refers").removeClass("active");
		  $.ajax({
			   type: 'POST',
			   url: BASE_URL + '/dashboard',
			   data: {
				   email: localStorage.getItem("email"),
				   password: localStorage.getItem("password"),
				   purpose: "seeConnections",
				   findRole: "mentor"
			   }
		   })
		   .done((result) => {
			   console.log("Mentor ka " + result);
			   console.log(result);
			   $(".people-lists").html('');
			   $(".show-connections").find('.show-people').show();
			   if(result.length<=1 && result[0].name==""){
				   $(".people-lists").html('<h3 class="text-center">No Requests Pending</h3>');
				   return;
			   }
			   const defaultList = '<li><h4 class="colu1 text-center">S.No.</h4><h4 class="colu2">Name</h4><h4 class="colu3 text-center">Status</h4></li><hr class="first-hr"/>';
			   $(".people-lists").html(defaultList);
			   let userEl = '';
			   result.map((user, index) => {
				   const del = '"';
				   userEl = '<li><p class="colu1 text-center">'+ parseInt(index+1) +'</p><p class="colu2">'+ user.name + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + user.role + '</p><p class="colu3 text-center"><button class="newMessage btn btn-primary" data-toggle="modal" data-target="#addMessageModal" onClick="localStorage.setItem(\'newMessage\', \''+ user.name +'\');localStorage.setItem(\'messageUser\', \'' + user.email + '\');">Message</button><img class="vdots-img" onclick="localStorage.setItem(\'messageUser\', \'' + user.email + '\');localStorage.setItem(\'messageUserName\', \'' + user.name + '\');" src="/img/vdots.png" data-toggle="modal" data-target="#viewMessages"/></p></li><hr class="next-hr"/>';
				   $(".people-lists").append(userEl);
			   });
			   $.ajax({
				   type: 'POST',
				   url: BASE_URL + '/dashboard',
				   data: {
					   email: localStorage.getItem("email"),
					   password: localStorage.getItem("password"),
					   purpose: "seeConnections",
					   findRole: "student"
				   }
			   })
			   .done((result) => {
				   console.log("Student ka " + result);
				   console.log(result);
				   $(".show-connections").find('.show-people').show();
				   if(result.length<=1 && result[0].name==""){
					   $(".people-lists").html('<h3 class="text-center">No Requests Pending</h3>');
					   return;
				   }
				   const defaultList = '<li><h4 class="colu1 text-center">S.No.</h4><h4 class="colu2">Name</h4><h4 class="colu3 text-center">Options</h4></li><hr class="first-hr"/>';
				   $(".people-lists").html(defaultList);
				   let userEl = '';
				   result.map((user, index) => {
					   const del = '"';
					   userEl = '<li><p class="colu1 text-center">'+ parseInt(index+1) +'</p><p class="colu2">'+ user.name + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + user.role + '</p><p class="colu3 text-center"><button class="newMessage btn btn-primary" data-toggle="modal" data-target="#addMessageModal" onClick="localStorage.setItem(\'newMessage\', \''+ user.name +'\');localStorage.setItem(\'messageUser\', \'' + user.email + '\');">Message</button><img class="vdots-img" onclick="localStorage.setItem(\'messageUser\', \'' + user.email + '\');localStorage.setItem(\'messageUserName\', \'' + user.name + '\');" src="/img/vdots.png" data-toggle="modal" data-target="#viewMessages" /></p></li><hr class="next-hr"/>';
					   $(".people-lists").append(userEl);
				   });
				   $(".newMessage").on('click', (e) => {
					  e.preventDefault();
					  $("#AddMessageLabel").html(localStorage.getItem("newMessage"));
					  $("#sendTo").val(localStorage.getItem("messageUser"));
				   });
				   $(".vdots-img").on('click', (e) => {
					  e.preventDefault();
					  console.log("Vdots clicked");
					  $.ajax({
						   type: 'POST',
						   url: BASE_URL + '/dashboard',
						   data: {
							   email: localStorage.getItem("email"),
							   password: localStorage.getItem("password"),
							   purpose: "viewAllMessages",
							   sentBy: localStorage.getItem("messageUser")
						   }
					   })
					   .done((result) => {
						   console.log(result);
						   const defaultMessageItem = '<div class="msg-item"><p>No Messages to Show</p></div>';
						   $(".message-items").html('');
						   if(result.length==1 && result[0].mid==-1){
							   $(".message-items").html(defaultMessageItem);
							   return;
						   }
						   result.map((msg, ind) => {
							   let writtenBy ;
							   if(msg.sentBy == localStorage.getItem("email")) {
								   writtenBy = localStorage.getItem("name");
							   } else {
								   writtenBy = localStorage.getItem("messageUserName");
							   }
							   const msgItem = '<div class="msg-item"><p>' + msg.message + '</p><p class="text-center">Written By: ' + writtenBy + '</p></div>';
							   $(".message-items").append(msgItem);
						   });
					   })
					   .fail(err => {
						   console.log(err);
						});
				   });
				   
			   })
			   .fail(err => {
				   console.log(err);
			   });
		   })
		   .fail(err => {
			   console.log(err);
		   });
   });
   
   $("#sidebar-manage-connect").on("click", (e) => {
	  e.preventDefault();
	  $("#profile").hide();
	  $("#forum").hide();
	  $("#connect").hide();
	  $("#inbox").hide();
	  $("#refers").hide();
	  $("#manage-connect").show();
	  while($("#sidebar-profile").hasClass("active")) $("#sidebar-profile").removeClass("active");
	  while($("#sidebar-forum").hasClass("active")) $("#sidebar-forum").removeClass("active");
	  while($("#sidebar-connect").hasClass("active")) $("#sidebar-connect").removeClass("active");
	  while($("#sidebar-inbox").hasClass("active")) $("#sidebar-inbox").removeClass("active");
	  if(!$("#sidebar-manage-connect").hasClass("active")) $("#sidebar-manage-connect").addClass("active");
	  while($("#sidebar-refers").hasClass("active")) $("#sidebar-refers").removeClass("active");
	  $.ajax({
		   type: 'POST',
		   url: BASE_URL + '/dashboard',
		   data: {
			   email: localStorage.getItem("email"),
			   password: localStorage.getItem("password"),
			   purpose: "getAllRequests"
		   }
	   })
	   .done(result => {
		   console.log("Manage Connections");
		   console.log(result);
		   if(result.length<=1 && result[0].name==""){
			   $(".people-lists").html('<h3 class="text-center">Nothing to Show</h3>');
			   return;
		   }
		   const defaultList = '<li><h4 class="colu1 text-center">S.No.</h4><h4 class="colu2">Name</h4><h4 class="colu3 text-center">Options</h4></li><hr class="first-hr"/>';
		   $(".people-lists").html(defaultList);
		   let userEl = '';
		   result.map((user, index) => {
			   console.log(user + " sd " + index);
			   const del = '"';
			   userEl = '<li><p class="colu1 text-center">'+ parseInt(index+1) +'</p><p class="colu2">'+ user.name + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + user.role + '</p><p class="colu3 text-center"><button type="button" onclick="localStorage.setItem(\'manage\', \''+ user.email +'\');localStorage.setItem(\'action\', \'accepted\');" class="btn btn-primary manage-people-action">Approve</button><button type="button" onclick="localStorage.setItem(\'manage\', \''+ user.email +'\');localStorage.setItem(\'action\', \'rejected\');" class="btn btn-primary manage-people-action">Reject</button></p></li><hr class="next-hr"/>';
			   $(".people-lists").append(userEl);
		   });
		   $(".manage-people").on('click', (e) => {
			   $.ajax({
				   type: 'POST',
				   url: BASE_URL + '/dashboard',
				   data: {
					   email: localStorage.getItem("email"),
					   password: localStorage.getItem("password"),
					   purpose: "manageRequest",
					   toApprove: localStorage.getItem("manage"),
					   action: localStorage.getItem("action")
				   }
			   })
			   .done((result) => {
				   console.log(result);
				   location.reload();
			   })
			   .fail(err => {
				   console.log(err);
			   })
		   });
			   
	   })
	   .fail(err => {
		   console.log(err);
	   })
   });
   
   $("#sidebar-refers").on('click', (e) => {
	  e.preventDefault();
	  $("#profile").hide();
	  $("#forum").hide();
	  $("#connect").hide();
	  $("#inbox").hide();
	  $("#manage-connect").hide();
	  $("#refers").show();
	  while($("#sidebar-profile").hasClass("active")) $("#sidebar-profile").removeClass("active");
	  while($("#sidebar-forum").hasClass("active")) $("#sidebar-forum").removeClass("active");
	  while($("#sidebar-connect").hasClass("active")) $("#sidebar-connect").removeClass("active");
	  while($("#sidebar-inbox").hasClass("active")) $("#sidebar-inbox").removeClass("active");
	  if(!$("#sidebar-refers").hasClass("active")) $("#sidebar-refers").addClass("active");
	  while($("#sidebar-manage-connect").hasClass("active")) $("#sidebar-manage-connect").removeClass("active");
	  if(localStorage.getItem("role")=="Mentor"){
		  $.ajax({
			  type: 'POST',
			  url: BASE_URL + '/dashboard',
			  data: {
				  email: localStorage.getItem("email"),
				  password: localStorage.getItem("password"),
				  purpose: "getAllUnis",
			  }
		  })
		  .done((result) => {
			  console.log("Unis lists");
			  console.log(result);
			  $(".people-lists").html('');
			  if(result.length<=1 && result[0].uid==0){
				   $(".people-lists").html('<h3 class="text-center">No Universities Available</h3>');
				   return;
			   }
			   const defaultList = '<li><h4 class="colu1 text-center">S.No.</h4><h4 class="colu2">Name</h4><h4 class="colu3 text-center">Refer</h4></li><hr class="first-hr"/>';
			   $(".people-lists").html(defaultList);
			   let userEl = '';
			   result.map((user, index) => {
				   const del = '"';
				   userEl = '<li><p class="colu1 text-center">'+ parseInt(index+1) +'</p><p class="colu2">'+ user.uname + '</p><p class="colu3 text-center"><button class="referUni btn btn-warning" data-toggle="modal" data-target="#referUni" onClick="localStorage.setItem(\'University\', \'' + user.uid + '\');">Refer</button></p></li><hr class="next-hr"/>';
				   $(".people-lists").append(userEl);
			   });
			   $(".referUni").on('click', (e) => {
				   $("#referUniModal").find("#email").val(localStorage.getItem("email"));
				   $("#referUniModal").find("#password").val(localStorage.getItem("password"));
				   $("#referUniModal").find("#referByName").val(localStorage.getItem("name"));
				   $("#referUniModal").find("#uid").val(localStorage.getItem("University")); 
			   });
			// show Universities
				  $.ajax({
					  type: 'POST',
					  url: BASE_URL + '/dashboard',
					  data: {
						  email: localStorage.getItem("email"),
						  password: localStorage.getItem("password"),
						  purpose: "getAllUnis",
					  }
				  })
				  .done((result) => {
					  console.log(result);
					  $(".uni-lists").html('');
					  if(result.length<=1 && result[0].uid==0){
						   $(".uni-lists").html('<h3 class="text-center">No Universities Available</h3>');
						   return;
					   }
					   const defaultList = '<li><h6 class="colu1 text-center">S.No.</h6><h4 class="colu2">Name of University</h4></li><hr class="first-hr"/>';
					   $(".uni-lists").html(defaultList);
					   let userEl = '';
					   result.map((user, index) => {
						   const del = '"';
						   userEl = '<li><p class="colu1 text-center">'+ parseInt(index+1) +'</p><p class="colu2">'+ user.uname + '</p></li><hr class="next-hr"/>';
						   $(".uni-lists").append(userEl);
					   });
				  })
				  .fail(err => {
					   console.log(err);
				   });
		  })
		  .fail(err => {
			   console.log(err);
		   })
	  } else {
		  // See the refers
		  $.ajax({
			  type: 'POST',
			  url: BASE_URL + '/dashboard',
			  data: {
				  email: localStorage.getItem("email"),
				  password: localStorage.getItem("password"),
				  purpose: "getAllRefers",
			  }
		  })
		  .done((result) => {
			  console.log(result);
			  $(".people-lists").html('');
			  if(result.length<=1 && result[0].uid==0){
				   $(".people-lists").html('<h3 class="text-center">No Refers Available</h3>');
				   return;
			   }
			   const defaultList = '<li><h6 class="colu1 text-center">S.No.</h6><h4 class="colu2">Name of University</h4><h4 class="colu3 text-center">Referred By</h4></li><hr class="first-hr"/>';
			   $(".people-lists").html(defaultList);
			   let userEl = '';
			   result.map((user, index) => {
				   const del = '"';
				   userEl = '<li><p class="colu1 text-center">'+ parseInt(index+1) +'</p><p class="colu2">'+ user.uname + '</p><p class="colu3 text-center">' + user.referByName + '</p></li><hr class="next-hr"/>';
				   $(".people-lists").append(userEl);
			   });
		  })
		  .fail(err => {
			   console.log(err);
		   });
		  
		  // show Universities
		  $.ajax({
			  type: 'POST',
			  url: BASE_URL + '/dashboard',
			  data: {
				  email: localStorage.getItem("email"),
				  password: localStorage.getItem("password"),
				  purpose: "getAllUnis",
			  }
		  })
		  .done((result) => {
			  console.log(result);
			  $(".uni-lists").html('');
			  if(result.length<=1 && result[0].uid==0){
				   $(".uni-lists").html('<h3 class="text-center">No Universities Available</h3>');
				   return;
			   }
			   const defaultList = '<li><h6 class="colu1 text-center">S.No.</h6><h4 class="colu2">Name of University</h4></li><hr class="first-hr"/>';
			   $(".uni-lists").html(defaultList);
			   let userEl = '';
			   result.map((user, index) => {
				   const del = '"';
				   userEl = '<li><p class="colu1 text-center">'+ parseInt(index+1) +'</p><p class="colu2">'+ user.uname + '</p></li><hr class="next-hr"/>';
				   $(".uni-lists").append(userEl);
			   });
		  })
		  .fail(err => {
			   console.log(err);
		   });
	  }
	  
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
				   console.log(result[0], q.qid);
				   let viewAll = '<a href="#" data-toggle="modal" id="viewAllAnswer" onClick="viewAllAnswer(this, '+ q.qid +', \'' +q.question +'\')" data-target="#allAnswerModal"><p>View All Answers</p></a>';
				   let answerContent, answerCredential = "", writeAnswer = "";
				   if(result[0].writerEmail!==localStorage.getItem("email")){
					   writeAnswer = '<button class="btn btn-success writeAnswer" onClick="selectQuestion(this, '+ q.qid +', \'' +q.question +'\')" data-toggle="modal" data-target="#addAnswerModal">Write an Answer</button>';
				   }
				   if(result[0].aid>0 && result[0]){
					   answerContent = result[0].answer;
					   answerCredential = '<div class="answer-credential text-center">Contributed By: ' + result[0].writer + ' | ' + result[0].writtenAt + '<div>';  
				   } else {
					   answerContent = '<p class="text-center">No Answers written</p>';
					   answerCredential = "";
				   }
				   const start = '<div class="question-item"><div class="title not-border" onclick="toggleBox(this, '+ q.qid +')"><div class="arrow"></div>';
				   const ques = '<div class="title-text">' + q.question + '</div><div class="question-credential">'+ q.userEmail +' | ' + q.createdAt.toLocaleString() + '</div></div>';
				   const end = '</div>';
				   const content = '<div class="content"><p>'+ answerContent +'</p>'+ viewAll + answerCredential + '<div>' + writeAnswer + '</div' +'</div>';
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
   
   /* Manage People connectivity */
   $(".show-people").hide();
   $(".show-connections").hide();
   $("#find-people").on("click", (e) => {
	   e.preventDefault();
	   $(".show-people").show();
	   $(".show-connections").hide();
	   $.ajax({
		   type: 'POST',
		   url: BASE_URL + '/dashboard',
		   data: {
			   email: localStorage.getItem("email"),
			   password: localStorage.getItem("password"),
			   purpose: "findPeople"
		   }
	   })
	   .done(result => {
		   console.log(result);
		   if(result.length<=1 && result[0].name==""){
			   $(".people-lists").html('<h3 class="text-center">Nothing to Show</h3>');
			   return;
		   }
		   const defaultList = '<li><h4 class="colu1 text-center">S.No.</h4><h4 class="colu2">Name</h4><h4 class="colu3 text-center">Status</h4></li><hr class="first-hr"/>';
		   $(".people-lists").html(defaultList);
		   let userEl = '';
		   result.map((user, index) => {
			   console.log(user + " sd " + index);
			   const del = '"';
			   userEl = '<li><p class="colu1 text-center">'+ parseInt(index+1) +'</p><p class="colu2">'+ user.name + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + user.role + '</p><p class="colu3 text-center"><button type="button" onclick="localStorage.setItem(\'friend\', \''+ user.email +'\')" class="btn btn-primary connect-people">Connect</button></p></li><hr class="next-hr"/>';
			   $(".people-lists").append(userEl);
		   });
		   $(".connect-people").on('click', (e) => {
			   e.preventDefault();
			   $.ajax({
				   type: 'POST',
				   url: BASE_URL + '/dashboard',
				   data: {
					   email: localStorage.getItem("email"),
					   password: localStorage.getItem("password"),
					   purpose: "connectPeople",
					   sentTo: localStorage.getItem("friend")
				   }
			   })
			   .done(result => {
				   console.log(localStorage.getItem("email") + " connected to " + localStorage.getItem("friend"));
				   location.reload();	
			   })
			   .fail(err => {
				   console.log(err);
			   });
		   });
	   })
	   .fail(err => {
		   console.log(err);
	   })
   });
   
   // See your connections
   $("#con-tab-student").on("click", (e) => {
	   e.preventDefault();
	   if(!$("#con-tab-student").hasClass("active")) $("#con-tab-student").addClass("active");
	   while($("#con-tab-mentor").hasClass("active")) $("#con-tab-mentor").removeClass("active");
	   $.ajax({
		   type: 'POST',
		   url: BASE_URL + '/dashboard',
		   data: {
			   email: localStorage.getItem("email"),
			   password: localStorage.getItem("password"),
			   purpose: "seeConnections",
			   findRole: "student"
		   }
	   })
	   .done((result) => {
		   console.log("Student ka " + result);
		   console.log(result);
		   $(".people-lists").html('');
		   $(".show-connections").find('.show-people').show();
		   if(result.length<=1 && result[0].name==""){
			   $(".people-lists").html('<h3 class="text-center">Nothing to Show</h3>');
			   return;
		   }
		   const defaultList = '<li><h4 class="colu1 text-center">S.No.</h4><h4 class="colu2">Name</h4><h4 class="colu3 text-center">Status</h4></li><hr class="first-hr"/>';
		   $(".people-lists").html(defaultList);
		   let userEl = '';
		   result.map((user, index) => {
			   const del = '"';
			   userEl = '<li><p class="colu1 text-center">'+ parseInt(index+1) +'</p><p class="colu2">'+ user.name + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + user.role + '</p><p class="colu3 text-center">Connected :)</p></li><hr class="next-hr"/>';
			   $(".people-lists").append(userEl);
		   });
	   })
	   .fail(err => {
		   console.log(err);
	   });
   });
   
   $("#con-tab-mentor").on("click", (e) => {
	  e.preventDefault();
	  while($("#con-tab-student").hasClass("active")) $("#con-tab-student").removeClass("active");
	  if(!$("#con-tab-mentor").hasClass("active")) $("#con-tab-mentor").addClass("active");
	  $.ajax({
		   type: 'POST',
		   url: BASE_URL + '/dashboard',
		   data: {
			   email: localStorage.getItem("email"),
			   password: localStorage.getItem("password"),
			   purpose: "seeConnections",
			   findRole: "mentor"
		   }
	   })
	   .done((result) => {
		   console.log("Student ka " + result);
		   console.log(result);
		   $(".people-lists").html('');
		   $(".show-connections").find('.show-people').show();
		   if(result.length<=1 && result[0].name==""){
			   $(".people-lists").html('<h3 class="text-center">Nothing to Show</h3>');
			   return;
		   }
		   const defaultList = '<li><h4 class="colu1 text-center">S.No.</h4><h4 class="colu2">Name</h4><h4 class="colu3 text-center">Status</h4></li><hr class="first-hr"/>';
		   $(".people-lists").html(defaultList);
		   let userEl = '';
		   result.map((user, index) => {
			   const del = '"';
			   userEl = '<li><p class="colu1 text-center">'+ parseInt(index+1) +'</p><p class="colu2">'+ user.name + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + user.role + '</p><p class="colu3 text-center">Connected :)</p></li><hr class="next-hr"/>';
			   $(".people-lists").append(userEl);
		   });
	   })
	   .fail(err => {
		   console.log(err);
	   })
   });
   
   $("#see-connections").on("click", (e) => {
	   e.preventDefault();
	   $(".show-people").hide();
	   $(".show-connections").show();
   });

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
});