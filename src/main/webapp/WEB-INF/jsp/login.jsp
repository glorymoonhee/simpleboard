<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<%
 String target = (String)request.getAttribute("target");
 
%>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<div><c:if test="${ error }">아이디와 이메일을 확인해주세요.</c:if></div>
	<form action="${pageContext.servletContext.contextPath}/doLogin"
		method="post">
		<c:if test="${not empty target }">
		<div>
		    <input type="hidden" name="target" value=<%=target%> />
		</div>
		</c:if>
		<div>
			id: <input type="text" name="userid" />
		</div>
		<div>
			password: <input type="password" name="pass" />
		</div>
		<input type="submit" value="로그인" />
	</form>
</body>
</html>