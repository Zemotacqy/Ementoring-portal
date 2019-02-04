const BASE_URL = "localhost:8000";
$( document ).ready(function() {
   const name =  $("#hidden_name").val();
   const role =  $("#hidden_role").val();
   const password =  $("#hidden_password").val();
   const email =  $("#hidden_email").val();
   const data = {
	   "email": email,
	   "password": password,
	   "purpose": "check"
   };
   $.ajax({
	   url: BASE_URL + "/login",
	   type: "POST",
	   data: JSON.stringify(data),
	   contentType: "application/json",
	   success: (res) => {
		   console.log(res);
	   },
	   error: (res) => {
		   console.log(res);
		   window.location = BASE_URL + "/login";
	   }
   });
   
});