<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxpath" value="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
<form action="${ctxpath}/login" method="post">
    <div>아이디: <input type="text" name="userid"/></div>
    <div>비밀번호: <input type="password" name="password"/></div>
    <input type="submit"/>
</form>
</body>
</html>