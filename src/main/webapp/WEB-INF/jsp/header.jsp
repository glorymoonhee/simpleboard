<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxpath" value="${pageContext.servletContext.contextPath}"></c:set>
<c:set var="user" value="${user}"/>
<div id="header">
    <span><a href="${ctxpath }">HOME</a></span> |
    <span><c:if test="${empty user}"><a href="${ctxpath }/join">JOIN</a></c:if><c:if test="${not empty user }"><a href="${ctxpath }/myinfo">내정보</a></c:if> |</span>
    <span><c:if test="${empty user}"><a href="${ctxpath }/login">로그인</a></c:if><c:if test="${not empty user }"><a href="${ctxpath }/logout">로그아웃</a></c:if></span>
</div>