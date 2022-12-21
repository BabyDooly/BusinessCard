var index = {
	init : function() {
		var _this = this;
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(function() {
			$(document).ajaxSend(function(e, xhr, options) {
				xhr.setRequestHeader(header, token);
			});
		});

		_this.pageChangeNum($('ul').data('nowpage'));

		$(document).on('click', 'ul li a', function() {
			_this.pageNumClick($(this).html().replace(/[^0-9]/g, '') - 1);
		});

		$(document).on('click', 'tr', function() {
			_this.pageChange('/notice/read?num=' + $(this).data('notice'))
		});

	},
	totalPage : $('ul').data('totalpage'),
	pageNumClick : function(nowpage) {
		var data = {
			page : nowpage
		};

		$.ajax({
			url : '/list',
			type : 'get',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			data : data
		}).done(function(data) {
			var eventTemplate = Handlebars.compile($('#list-template').html());
			$('tbody').html($(eventTemplate({
				notice : data
			})));
			index.pageChangeNum(nowpage);
		}).fail(function(error) {
			console.log(error);
		});
	},
	pageRange : function(nowpage) {
		var minpage = nowpage - 4;
		var maxpage = nowpage + 4;

		if (minpage <= 0)
			minpage = 1;
		if (maxpage >= this.totalPage)
			maxpage = this.totalPage;

		var range = [];
		for (; minpage <= maxpage; minpage++) {
			range.push(minpage);
		}

		return range;
	},
	pageChangeNum : function(nowpage) {
		var page = $('ul');
		nowpage = nowpage + 1;
		$('ul li').remove();

		this.pageRange(nowpage).forEach(function(value, index) {
			var li = $('<li/>');

			if (value == nowpage)
				li.addClass('is-active');
			li.append($('<a/>', {
				text : value
			}));
			page.append(li);
		});
	},
	pageChange : function(href) {
		location = href;
	}
};
index.init();