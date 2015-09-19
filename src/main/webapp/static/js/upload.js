/**
 * 
 */

function FileUploader () {
	var $ = jQuery || $;
	
	function getFormData ( formId ) {
		var $inputs = $('input', formId);
		var fd = new FormData();
		var cnt = 0;
		$inputs.each ( function(idx){
			var $i = $(this);
			if ( !$i.attr('name')){
				return ;
			}
			
			var type = $i.attr('type');
			
			if ( type === 'file') {
				$.each ( this.files, function(i, file){
					fd.append('file-' + i , file);
				})
			} else {
				fd.append($i.attr('name'), this.value );
			}
		});
		
		/*
		$.each( $files , function (idx, obj ){
			$.each(obj.files, function(k, file) {
				fd.append('file-' + idx, file);
		    });
		});
		*/
		return fd;
	}

	function sendFiles (url, formId  ) {
//		var url = '/simpleboard/doUpload';
//		var fs =  $('#frmUpload' );
//		var fs = $( formId );
		var fd = getFormData( formId );
		
		$.ajax ({
			url: url, 
			type: 'POST',
			data: fd,
			processData: false,

			
			success: function(response){
				console.log( response );
			},
			error : function ( xhr, status, error ){
				console.log( xhr, status, error);
			}
		});
	}
	
	return {
		sendFiles : sendFiles 
	};
}