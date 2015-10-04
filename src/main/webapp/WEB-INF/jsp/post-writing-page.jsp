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

var s = functionName();

function Student () {
	
}


function sendPost ( ) {
	var uri = ctxpath + "/post/write.ajax";

	
   $("form#frmPost").submit(function (){
	   var formData = new FormData( this );

	    $.ajax({
	        url: uri,
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function(response){
				console.log( response );
				document.location.href = response.nextUrl;
			},
			error : function ( xhr, status, error ){
				console.log( xhr, status, error);},
	        cache: false,
	        contentType: false,
	        processData: false
	    });

	    return false;
   }).trigger('submit');
	
}

$(document).ready( function(){
	$('#btnSubmit').click(  sendPost );
});
</script>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div id="error"></div>
<form method="post" id="frmPost" accept-charset="utf-8">
	제목 : <input type="text" size="20" id="title" name="title"/> <br />
	내용 : <textarea rows="20" cols="30" id="content" name="content"></textarea>
	<div><input type="file" id="files" name="files"></div>
	<div></div><input type="button" value="완료" id="btnSubmit"/></div>
</form>
</body>
</html>