<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="${pageContext.servletContext.contextPath }"/>
<c:set var="user" value="${user}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>첫페이지입니다.</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
첫 페이지 잘 나오나?
<div><img src="${ctxpath}/static/images/ed-3.png" /></div>
</body>
</html>