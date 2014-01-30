$(function() {
	$("button.device").click(
			function(e) {
				e.preventDefault();
				var level = $(this).attr('level');

				$.get("action?did=" + $(this).attr('did')
						+ "&action=dim&level=" + level);
				$(this).parent().children().removeClass('btn-default');
				$(this).addClass('btn-default');
				$(this).parent().parent().parent().parent().find(
						"button.room[level='" + level + "']").addClass(
						'btn-default');
			});
	$("button.room").click(
			function(e) {
				e.preventDefault();
				$.get("action?room=" + $(this).attr('room')
						+ "&action=dim&level=" + $(this).attr('level'));

				$(this).parent().children().removeClass('btn-default');
				$(this).addClass('btn-default');

				var level = $(this).attr('level');
				var ul = $(this).parent().parent().children("ul");
				$(ul).find("button").removeClass('btn-default');
				$(ul).find("button[level='" + level + "']").addClass(
						'btn-default');
			});
	$("button.house").click(function(e) {
		e.preventDefault();
		var level = $(this).attr('level');
		$.get("action?house=1&action=dim&level=" + $(this).attr('level'));
		$("button").removeClass('btn-default');
		$("button[level='" + level + "']").addClass('btn-default');
	});
	$(".showRoom").click(function(e) {
		e.preventDefault();
		$(this).next().toggle();
		if ($(this).children("span").hasClass("glyphicon-chevron-down")) {
			$(this).children("span").removeClass("glyphicon-chevron-down");
			$(this).children("span").addClass("glyphicon-chevron-up");
		} else {
			$(this).children("span").removeClass("glyphicon-chevron-up");
			$(this).children("span").addClass("glyphicon-chevron-down");
		}
	});
});
