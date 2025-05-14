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
	
	<p><c:out value="${user.userName }" />のトーク</p>
	
	<table>
		<tr>
			<c:if test="${not empty userChatLog }">
				<c:out value="${userChatLog }"/>
			</c:if>
		</tr>
		<tr>
			<p>だから、なんでやねん！！</p>
		</tr>
	</table>
	
</body>
</html>