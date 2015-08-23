<%@page import="kmj.webboard.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 완료</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h1>회원가입이 완료되었습니다.</h1>
${requestScope.user1.userId} 님 환영합니다.
</body>
</html>