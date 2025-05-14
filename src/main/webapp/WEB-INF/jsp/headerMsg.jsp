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
	
	<c:if test="${not empty successMsg }">
		<c:out value="${successMsg }" />
	</c:if>
	<c:if test="${not empty errorMsg }">
		<c:out value="${errorMsg }" />
	</c:if>

</body>
</html>