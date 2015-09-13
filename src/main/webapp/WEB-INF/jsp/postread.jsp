<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:set var="ctxpath" value="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/part/common-js.jsp"/>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글보기</title>
</head>
<script type="text/javascript">
 
 $(document).ready( function(){
	 $("#delete").click( function(){
		$.post('/simpleboard/post/delete/${post.seq}',
				$('#frmPost').serialize()
				,function(response){
			if(response.success){
				console.log('페이지 이동하라.여기까지넘어옴');
				 document.location.href = response.nextURL;
			}
		});
	 });
	 
 });

</script>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<form method="post" id="frmPost">
<c:if test="${not empty post }">
 제목: ${post.title} <br/>
 내용: ${post.content}
user in session : ${sessionScope.user.seq }, xx : ${post.writer.seq }
<c:if test="${sessionScope.user.seq eq post.writer.seq}">
 
<a href="${ctxpath}/post/edit/${post.seq}" >수정하기</a>
<a href="" id="delete">삭제하기</a>
<%-- ${ctxpath}/post/delete/${post.seq} --%>

</c:if> 
</c:if>
<c:if test="${empty post}">
존재하지 않는 글입니다.
</c:if>
</form>
</body>
</html>