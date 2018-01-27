$(function(){
	$("#btn-index").click(function(){
		go("/index.html");
	});
	
	$(".ui-icon-close").click(function(){
		$(this).parent().find("input[type=text]").val("");
	});
	
	$("#btn-orders").click(function() {
    	go("/orders.html");
    });
    
    $("#btn-myclub").click(function() {
    	go("/myclub.html");
    });
    
    $("#btn-tocharge").click(function() {
    	go("/tocharge.html");
    });
    
    oauth2.ajax({
    	url: "/account/info",
    	success: function(response){
    		var data = response.data;
    		console.info(data);
    		$("#info-balance").html(data.balance); 
    		$("#info-nickname").html(data.nickname);
    		$("#info-headimgurl").css("background-image","url("+data.headimgurl+")");
    	}
    });
    
    $("#btn-logout").click(function() {
    	oauth2.logout();
    });
});