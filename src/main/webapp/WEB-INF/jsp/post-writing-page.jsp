<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/jsp/part/common-js.jsp"/>
<style type="text/css">
#error {
	background-color: FF99AA;
}
</style>
<script type="text/javascript">
var ctxpath = '/simpleboard';

function sendPost ( ) {
	var uri = ctxpath + "/post/write.ajax";
	/*
	var params = {title: '', content: ''};
	params.title = $('#title').val();
	params.content = $('#content').val();
	console.log(params);
	*/
	
	// VALIDATION
	/* if ( checkTitle( $('#title').val()) ) {
		;
	}
	if ( checkContent( $('#content').val()) ) {
		;
	} */
	
	/* $.ajax({
		uri : uri, 
		method : 'POST',
		enctype : 'multipart/form-data',
		success : function ( response ) {
			;
		},
		error : function( ) {
			;
		}
	}); */
	
	$.post( uri,
			$('#frmPost').serialize(),
			function(response) {
		console.log("[응답]", response);
		if(response.success ){
			// { success: true, nextUrl: '/sssss' }
			console.log('잘 넣었음');
			document.location.href = response.nextUrl;
		} else {
			// { success: false, cause: 'in login info' }
			$('#error').html('<p>' + response.cause + '</p>');
		}
	});
}

$(document).ready( function(){
	 $('#btnSubmit').click(  sendPost );
	 
});
</script>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div id="error"></div>
<form method="post" id="frmPost">
	제목 : <input type="text" size="20" id="title" name="title"/> <br />
	내용 : <textarea rows="20" cols="30" id="content" name="content"></textarea>
	<!-- <div><input type="file"></div> -->
	<div></div><input type="button" value="완료" id="btnSubmit"/></div>
</form>
</body>
</html>