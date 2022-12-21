var read={
	init:function(){
		var _this=this;
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(function() {
		    $(document).ajaxSend(function(e, xhr, options) {
		        xhr.setRequestHeader(header, token);
		    });
		});

		_this.commentRead();
	},
	noticeUpdate : function() {
		location.href="/notice/update/?num="+$('.notice').data('notice');
	},
	commentSave : function() {
		var data = {
			notice : $('.notice').data('notice'),
			contents : $('#writeComment').val(),
		}
		$('#writeComment').val('');
		$.ajax({
			url:'/comment/write',
			type:'post',
			dataType:'json',
			contentType:'application/json; charset=utf-8',
			data:JSON.stringify(data)
		}).done(function(data){
			var eventTemplate=Handlebars.compile($('#comment-template').html());
			$('#comment').html($(eventTemplate({comment:data})));
			alert('댓글 작성 완료');
		}).fail(function(error){
			console.log(error);
		});
	},
	commentRead : function() {
		var data = {
			num : $('.notice').data('notice')
		};

		$.ajax({
			url : '/comment/read',
			type : 'get',
			data : data,
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
		}).done(function(data) {
			var eventTemplate = Handlebars.compile($('#comment-template').html());
			$('#comment').html($(eventTemplate({comment : data})));
		}).fail(function(error) {
			console.log(error);
		});
	}
}
read.init();