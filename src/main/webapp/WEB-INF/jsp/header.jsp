<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="${pageContext.servletContext.contextPath }"/>
<c:set var="user" value="${sessionScope.user}"/> 
<div>
	<span><a href="${ctxpath}/">HOME</a></span> |
	<span>
	        <a href="${ctxpath}/post/all">글보기</a>
	</span> |
	<span>
		<c:if test="${empty user}"><a href="${ctxpath}/login">LOGIN</a></c:if>
	    <c:if test="${not empty user}"><a href="${ctxpath }/logout">LOGOUT</a></c:if>
	</span> |
	<span>
	        <c:if test="${empty user}"><a href="${ctxpath}/join">JOIN</a></c:if>

	</span> |
	<span>
	        <c:if test="${not empty user}"><a href="${ctxpath}/post/write">글쓰기</a></c:if>

	</span> |
	<span><c:if test="${not empty user}"><a href="${ctxpath }/myInfo">${user.userId}님</a></c:if></span>
	<span><a href="${ctxpath}/help">HELP</a></span>
</div>