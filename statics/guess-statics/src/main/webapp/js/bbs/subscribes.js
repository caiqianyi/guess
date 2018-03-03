$(function(){
	
	$('.ui-searchbar').tap(function(){
        go("/bbs/search.html");
    });
	
    $("#btn-tab-recommend").tap(function(){
    	go("/bbs/recommend.html");
    });
    
    $(".post").click(function(){
    	go("/bbs/post.html");
    });
});