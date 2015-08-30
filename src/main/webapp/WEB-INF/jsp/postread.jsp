<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/part/common-js.jsp"/>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글보기</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<c:if test="${not empty post }">
 제목: ${post.title} <br/>
 내용: ${post.content}
user in session : ${sessionScope.user.seq }, xx : ${post.writer.seq }
<c:if test="${sessionScope.user.seq eq post.writer.seq}">
 <input type="button" value="수정"/>
</c:if> 
</c:if>
<c:if test="${empty post}">
존재하지 않는 글입니다.
</c:if>
</body>
</html>