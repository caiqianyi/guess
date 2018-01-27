$(function(){
	$('.ui-searchbar').tap(function(){
        $('.ui-searchbar-wrap').addClass('focus');
        $('.ui-searchbar-input input').focus();
    });
    $('.ui-searchbar-cancel').tap(function(){
        $('.ui-searchbar-wrap').removeClass('focus');
    });
    
    $(".ui-icon-search").click(function(){
    	go("/search.html?word="+$('.ui-searchbar-input input').val());
    });
    
    $(".ui-icon-close").click(function(){
		$(this).parent().find("input[type=text]").val("");
	});
    
    $("#btn-index").click(function(){
		go("/index.html");
	});
    
    $("#btn-personal").click(function() {
    	go("/personal.html");
    });
    $("#btn-myclub").click(function() {
    	go("/myclub.html");
    });
});