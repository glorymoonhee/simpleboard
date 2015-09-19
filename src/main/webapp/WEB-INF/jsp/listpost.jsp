<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxpath" value="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#postTable {
	background-color: #FFAA00;
	width: 800px;
} 
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<script type="text/javascript">

var ctxpath = '${ctxpath}';
// 객체 리터럴이라고 합니다.
var obj = {
	name : 'james',
	age  : 30,
	address : 'seoul'
}

var response = {
		success : true,
		posts : [ {title: 'first', content: 'first content'}, 
		          {title: 'second', content: 'second content'}
		]
}
/**
 *
 seq,
 title,
 content,
 date,
 writer
 viewcount
 
 <tr>
	<th>글번호</th>
	<th>글제목</th>
	<th>내용</th>
	<th>글쓴이</th>
	<th>작성일</th>
	<th>조회수</th>
</tr>
 
 */
 /**
  *
  */
  
  
  
  
  
  
 
function renderPosts ( aa ) {
	// .size()   java.util.List.size()
	// .length
	 
	for ( var i = 0 ; i < aa.length; i++) {
		var s = "<tr>";
		
		s += "<td>"  + aa[i].seq +  "</td>";
		s += "<td>"  + aa[i].title +  "</td>";
        s += "<td><a href='${ctxpath}/post/read/" +  aa[i].seq + "'>"  + aa[i].content + "</a></td>";    
		//s += "<td>"  + aa[i].content +  "</td>";
        s += "<td>"  + aa[i].writer +  "</td>";
		s += "<td>"  + aa[i].date +  "</td>";
		s += "<td>"  + aa[i].viewcount +  "</td>";
		s += "</tr>";	
		
		$('#postTable > tbody:last').append(s );
		
		
	
	}

}

function sendPostRequest ( ) {
	/*
	  response = ...;
	  fn(response);
	*/
	console.log("OK???????");
	$.get('/simpleboard/post.ajax', function( response ){
		console.log(response);
		if ( response.success) {
			console.log('응답왔음', response);
			renderPosts ( response.posts );
		} else {
			alert ( response );
		}
	}).fail( function(jqXHR, textStatus, errorThrown) {
		  if (textStatus == 'timeout')
			    console.log('The server is not responding');

			  if (textStatus == 'error')
			    console.log(errorThrown);

			  // Etc
	});
}


	 
	
$(document).ready (  function(e, a, value ) {
	$('#btnPrev').click ( function() {
		console.log('prev');
	});
	
	$('#btnNext').bind('click', function(e){
		console.log('next');
	});
	
	$('#btnWrite').click( function(){
		document.location.href = ctxpath + '/post/write';
	});
	
	sendPostRequest();
	



	    
});

</script>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
<div>전체 게시판</div>
<input id ="btnWrite" type="button" value="글쓰기"/>
<table id="postTable">
	<tr>
		<th>글번호</th>
		<th>글제목</th>
		<th>내용</th>
		<th>글쓴이</th>
		<th>작성일</th>
		<th>조회수</th>

	</tr>
	<tbody>
	</tbody>
	<!-- 
	<tr>
		<td>첫번째 글입니다.</td>
		<td>제임스</td>
	</tr>
	<tr>
		<td>첫번째 글입니다.</td>
		<td>제임스</td>
	</tr>
	<tr>
		<td>첫번째 글입니다.</td>
		<td>제임스</td>
	</tr>
	<tr>
		<td>첫번째 글입니다.</td>
		<td>제임스</td>
	</tr>
	 -->
</table>
<div id="controlPanel">
<input type="button" id="btnPrev" value="이전페이지"/>
<input type="button" id="btnNext" value="다음페이지"/>
</div>
</body>
</html>