$(function(){
	$("#btn-toreply").click(function(){
		$("#pb_poster_layer").animate({ left: 0 }, 200, null, function(){
			$("#main").hide();
		});
	});
	
	$("#btn-reply-return").click(function(){
		$("#main").show(function(){
			$("#main").removeAttr("style");
			$("#pb_poster_layer").animate({ left: "100%" }, 200, null, function(){
				console.info(2);
			});
		});
	});
	
	$(".fload_more_btn").click(function(){
		go("/bbs/replys.html");
	});
});