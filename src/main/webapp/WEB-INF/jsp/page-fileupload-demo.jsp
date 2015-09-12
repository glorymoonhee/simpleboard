<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/part/common-js.jsp"/>
<title>파일업로드테스트입니다.</title>
<script type="text/javascript">
function sendFiles ( ) {
	var url = '/simpleboard/doUpload';
	var fd = new FormData();
	var fs =  $('input[type=file]' );
	$.each( fs, function (idx, obj ){
		$.each(obj.files, function(k, file) {          
			fd.append('file-' + idx, file);
	    });
	});
	
	$.ajax ({
		url: url, 
		type: 'POST',
		data: fd,
		processData: false,
		contentType: false,
		enctype: 'multipart/form-data',
		success: function(response){
			console.log( response );
		},
		error : function ( xhr, status, error ){
			console.log( xhr, status, error);
		}
	});
}
$(document).ready ( function(){
	$('.files').bind('change', function(){
		console.log( this.files[0].size );
	});
	
	$('#btnUpload').click ( sendFiles );
});
</script>
</head>
<body>
<form id="frmUpload" method="post" enctype="multipart/form-data">
	<input class="files" type="file" name="upfile"/> <br/>
	<input class="files" type="file" name="upfile"/> <br/>
	<input type="text" name="desc"/>
	<input id="btnUpload" type="button" value="올리기"/>
</form>
</body>
</html>