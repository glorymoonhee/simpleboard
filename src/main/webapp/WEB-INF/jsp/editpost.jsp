<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/part/common-js.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
 $(document).ready( function(){

    $("#btnSubmit").click( function(){
       
    	//var params = { seq: post.seq, title : $('#title').val(), content : "dddd" };
    	$.post('/simpleboard/post/update.ajax',
    			$("#frmPost").serialize(), 
    			function(response){
    		        
    		         if(response.success){
    		        		console.log('update성공');
    		        	 document.location.href = response.nextURL; 
    		        	 
    		         }
    	}) ;
    	
    });
    

 });
 
</script>

<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div id="error"></div>
<form method="post" id="frmPost">
	<input type="hidden" name="seq" value="${post.seq}"/>
	제목 : <input type="text" size="20" id="title" name="title" value="${post.title}"/> <br />
	내용 : <textarea rows="20" cols="30" id="content" name="content">${post.content}</textarea>
	<input type="button" value="완료" id="btnSubmit"/>
</form>
</body>
</html>