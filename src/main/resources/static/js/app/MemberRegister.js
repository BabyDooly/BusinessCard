var register = {
	init : function() {
		var _this=this;
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(function() {
		    $(document).ajaxSend(function(e, xhr, options) {
		        xhr.setRequestHeader(header, token);
		    });
		});
	},
	isIdCheck:function(){
		$.ajax({
			url:'/member/idcheck',
			type:'post',
			dataType:'json',
			contentType:'application/json; charset=utf-8',
			data:$('#id').val()
		}).done(function(data){
			console.log(data);
			if(data){
				alert('없는 ID입니다');
			}else{
				alert('중복되는 ID입니다');
				$('#id').val('');
			}
		}).fail(function(error){
			console.log(error);
		});
	}
}

register.init();