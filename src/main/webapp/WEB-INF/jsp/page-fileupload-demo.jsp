<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/part/common-js.jsp"/>
<script type="text/javascript" src="/simpleboard/static/js/upload.js"></script>
<title>파일업로드테스트입니다.</title>
<script type="text/javascript">


var uploader = FileUploader();

$(document).ready ( function(){
	$('.files').bind('change', function(){
		var $output = $('#output');
		var totalSize = 0;
		var html = '';
		for ( var idx = 0 ; idx < this.files.length; idx++){
			html += '<div>' + this.files[idx].name + '(' + this.files[idx].size + ' bytes)</div>';
			totalSize += this.files[idx].size;
		}
		html += '<div>total: ' + totalSize + 'bytes</div>';
		$output.html ( html );
		console.log( this.files[0].size );
	});
	
	$('#btnUpload').click ( function() {
		uploader.sendFiles ('/simpleboard/doUpload', '#frmUpload');
	} );
});
</script>
</head>
<body>
<form id="frmUpload" method="post" enctype="multipart/form-data">
	<input class="files" type="file" name="upfile" multiple /> <br/>
	<input type="text" name="desc"/>
	<input id="btnUpload" type="button" value="올리기"/>
	<div id="output">
	</div>
</form>
</body>
</html>