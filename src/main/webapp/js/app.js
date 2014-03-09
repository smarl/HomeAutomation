var determineLevel = function(levelDiv, clickEvent) {
	console.log( 'clickEvent.offsetX=' + clickEvent.offsetX );
	console.log( 'levelDiv.offsetWidth=' + levelDiv.offsetWidth );

	result = Math.round( clickEvent.offsetX/levelDiv.offsetWidth*100.0 );
	if( result < 10 ) {
		result = 0;
	}
	if( result > 90) {
		result = 100;
	}
	console.log( 'result=' + result );

	return result;
};

var setSliderLevel = function(pb, level) {
	pb.attr('aria-valuenow', level);
	pb.width( level + '%' );
	pb.find('.levelLabel').setText( '&nbsp;' + level + '%' );
};

var setLevel = function(level, el) {
	var dest = $(el).attr('dest');
	$.get("action?" + dest + "=" + $(el).attr('actionableId')
			+ "&action=dim&level=" + level);
};

$(function() {
	$(".levels").click(
		function(e) {
			e.preventDefault();
			var level = determineLevel(this,e);
			var dest = $(this).attr('dest');

			setLevel(level, this);

			if( dest === 'house' ) {
				setSliderLevel($('body').find('.progress-bar') , level);
			} else {
				setSliderLevel($(this).parent('li').find('.progress-bar') , level);
			}


//			$(this).parent().children().removeClass('btn-default');
//			$(this).addClass('btn-default');
//			$(this).parent().parent().parent().parent().find(
//					"button.room[level='" + level + "']").addClass(
//					'btn-default');
		});

//	$("button.device").click(
//			function(e) {
//				e.preventDefault();
//				var level = $(this).attr('level');
//
//				$.get("action?did=" + $(this).attr('did')
//						+ "&action=dim&level=" + level);
//				$(this).parent().children().removeClass('btn-default');
//				$(this).addClass('btn-default');
//				$(this).parent().parent().parent().parent().find(
//						"button.room[level='" + level + "']").addClass(
//						'btn-default');
//			});
//	$("button.room").click(
//			function(e) {
//				e.preventDefault();
//				$.get("action?room=" + $(this).attr('room')
//						+ "&action=dim&level=" + $(this).attr('level'));
//
//				$(this).parent().children().removeClass('btn-default');
//				$(this).addClass('btn-default');
//
//				var level = $(this).attr('level');
//				var ul = $(this).parent().parent().children("ul");
//				$(ul).find("button").removeClass('btn-default');
//				$(ul).find("button[level='" + level + "']").addClass(
//						'btn-default');
//			});
//	$("button.house").click(function(e) {
//		e.preventDefault();
//		var level = $(this).attr('level');
//		$.get("action?house=1&action=dim&level=" + $(this).attr('level'));
//		$("button").removeClass('btn-default');
//		$("button[level='" + level + "']").addClass('btn-default');
//	});
	$(".showRoom").click(function(e) {
		e.preventDefault();
		$(this).next().next().toggle();
		if ($(this).children("span").hasClass("glyphicon-chevron-down")) {
			$(this).children("span").removeClass("glyphicon-chevron-down");
			$(this).children("span").addClass("glyphicon-chevron-up");
		} else {
			$(this).children("span").removeClass("glyphicon-chevron-up");
			$(this).children("span").addClass("glyphicon-chevron-down");
		}
	});
});
