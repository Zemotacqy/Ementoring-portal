<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>E-Mentoring | Login</title>
</head>
<body>
This is a first web page : ${name}
<form action="/dashboard" method="post">
Enter your email: <input type="text" name="email"/> Enter your Password: <input type="password" name="password"/> <input type="submit" value="Login"/>
</form>
</body>
</html>