<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="${pageContext.servletContext.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.js"></script>
<title>게시판</title>
<script type="text/javascript">
	var ctxpath = ctxpath || '${ctxpath}';
	
	function renderPosts(posts) {
		alert ( posts);
		//$('#mytable > tbody:last > tr:last').remove();
		$('#mytable > tbody:last').append('<tr><td>제목이다</td><td>글쓴이다.</td></tr>');
	}
	function reqPost() {
	      /*$('#frm').serialize(),*/
        /*
         response = {"user":{"email":"dddd@naver.com","userid":"ddddd","password":"12345"},"success":true};
         */
		$.get(ctxpath + "/post.ajax", function(response) {
			alert(response);
			if (response.success) {
				renderPosts( response.posts);
			} else {
				alert('페이지 로드 실패 : ' + response.cause);
			}
		});
	}
	
	$(document).ready( function() {
		reqPost();
	});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
<table id="mytable">
    <tr>
        <th>제목</th>
        <th>글쓴이</th>
    <tr>
</table>
</body>
</html>