$(function(){
	
    $("#btn-tab-subscribe").tap(function(){
    	go("/bbs/subscribes.html");
    });
    
    $(".detail-bottom").click(function(){
    	go("/bbs/paste.html");
    });
});