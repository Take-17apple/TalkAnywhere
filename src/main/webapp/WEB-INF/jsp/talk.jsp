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
	
	<form action="TalkServlet" method="post" class="tali-textarea">
		<textarea name="chat"></textarea>	
		<button type="submit">送信</button><br>
	</form>
	
	<c:if test="${not empty userChatLog }">
	<c:if test="${not empty aiChatLog }">
		<c:forEach var="chat" items="${userChatLog.chatHistory }">
		<c:forEach var="ai" items="${aiChatLog.chatHistory }">
			<div class="chat">
				<p class="chat me"><c:out value="${chat }"/></p><br>
				<p class="chat you"><c:out value="${ai }"/></p><br>
				 <!-- ここでループを終了させたい条件 -->
    			<c:if test="${conditionToBreak}">
              		<c:set var="exitFlag" value="true" />
                </c:if>
                <!-- exitFlagがtrueの場合、内側のループを終了 -->
            	<c:if test="${exitFlag}">
                	<c:break />
             	</c:if>
			</div>
		</c:forEach>
		</c:forEach>
	</c:if>
	</c:if>
	
	
	<c:if test="${empty userChatLog }">
		<p>トークが空です</p>
	</c:if>
		
</body>
</html>