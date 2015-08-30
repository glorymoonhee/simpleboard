<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

/* 

function renderUsers( users ){
	for(var i =0 ; i < users.length; i++){

		var s = "<tr>";
		s+= "<td>"+ users[i].userid  +"</td>";
		s+= "<td>"+ users[i].useremail  +"</td>";
	    s+= "</tr>";
	    
	    
	    $('#usersTable > tbody:last').append(s);
	} 
	
} */
         
function renderUsers ( users ) {
	for ( var i = 0 ; i < users.length; i++) {
		var s = "<tr>";
		
		var s = "<tr>";
		s+= "<td>"+ users[i].userid  +"</td>";
		s+= "<td>"+ users[i].useremail  +"</td>";
	    s+= "</tr>"
	    
		
		$('#usersTable > tbody:last').append(
				s  );
	}
}



function allusers(e){

$.get('/simpleboard/user.ajax',function(response){
	if(response.success){
		console.log('응답왔음');
        renderUsers(response.users);
	
	}else{
		;
	}
});

}


$(document).ready( function(e) {
	$('#btnPrev').click(function(e){
		console.log('이전버튼');
	});
	$('#btnNext').click(function(e){
		console.log('다음버튼');
	});
	
	allusers();
} );








</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<table id="usersTable">
<tr>
<th>user ID</th>
<th>user Email</th>
</tr>

<%-- 
 <c:forEach items="${requestScope.alluser }" var="u">
	<tr> <td>ID: ${u.userId }</td> <td>Email: ${u.email }</td></tr>
</c:forEach>
 --%>
<tbody></tbody>

</table>


<input type="button" id="btnPrev" value="이전페이지">
<input type="button" id="btnNext" value="다음페이지">

</body>
</html>