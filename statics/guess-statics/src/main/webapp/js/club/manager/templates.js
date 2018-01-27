$(function(){

	$("#btn-index").click(function(){
		go("/index.html");
	});
	
	$(".ui-icon-close").click(function(){
		$(this).parent().find("input[type=text]").val("");
	});
	
	var clubId = GetQueryString("clubId");
	
	oauth2.ajax({
		type: 'POST',
		url: '/guess/template/findByClubId/'+cludId,
		dataType: 'json',
		success: function(response){
			
		}
	});
	
	var tab = new fz.Scroll('.ui-tab', {
        role: 'tab',
        autoplay: false
    });
	
});