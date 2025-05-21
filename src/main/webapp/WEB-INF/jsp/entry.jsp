<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<title>Talk Anywhere</title>
</head>
<body>
<%@ include file="headerMsg.jsp" %>
	
	<h1>登録</h1>
	<form action="EntryServlet" method="post" class="login-form">
		<label for="userName">Name</label>
			<input type="text" name="userName" id="userName" placeholder="Enter your name" required><br>
		<label for="password">Password</label>
			<input type="password" name="password" id="password" placeholder="Please enter your password" required><br>
		<button type="submit">登録</button><br>
	</form>
	<a href="LoginServlet" class="a-link">ログイン</a>
	
</body>
</html>