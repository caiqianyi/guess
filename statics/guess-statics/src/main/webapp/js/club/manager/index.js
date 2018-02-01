$(function(){
	var club = null;
	var clubId = GetQueryString("clubId");
	
	oauth2.ajax({
		type: 'GET',
		url: '/guess/club/info',
		data: {clubId:clubId},
		dataType: 'json',
		success: function(response){
			club = response.data;
			if(!club){
				alert("俱乐部不存在！");
				go("/myclub.html");
				return;
			}
			var html = "";
			html += '<div class="ui-avatar-lg">';
    		if(club.icon){
    			html += '		<span style="background-image: url(http://placeholder.qiniudn.com/290x160)"></span>';
    		}else{
    			html += '		<span style="background-image: url(/img/club/'+club.kindOf+'.jpg)"></span>';
    		}
    		html += '	</div>';
    		html += '	<p>'+club.name+'</p>';
    		html += '	<p>余额：'+club.cardNum+'</p>';
    		
    		$("#h-title").html(club.name);
    		$("#club-info").html(html);
		}
	});
	
    $(".ui-icon-close").click(function(){
		$(this).parent().find("input[type=text]").val("");
	});
    
    $("#btn-club-entrance").click(function(){
		if(club){
			go('/club/'+club.kindOf+'.html?clubId='+clubId);
			return;
		}
		go("/index.html");
	});
    
    $("#btn-tomembers").click(function(){
    	go("/club/manager/members.html?clubId="+clubId);
    });
    
    $("#btn-toliveness").click(function(){
    	go("/club/manager/liveness.html?clubId="+clubId);
    });
    
    $("#btn-toorders").click(function(){
    	go("/club/manager/orders.html?clubId="+clubId);
    });
    
    $("#btn-totemplates").click(function(){
    	go("/club/manager/topic/list.html?clubId="+clubId);
    });
    
    $("#btn-toclubInfo").click(function(){
    	go("/club/manager/info.html?clubId="+clubId);
    });
    
    $("#btn-dissolution-toggle").click(function(){
    	$('.ui-actionsheet').addClass('show');
    });
    
    $("#btn-dissolution-confirm").click(function(){
    	oauth2.ajax({
    		type: 'GET',
    		url: '/guess/club/delete',
    		data: {clubId:clubId},
    		dataType: 'json',
    		success: function(response){
    			alert("解散成功！");
    			go("/myclub.html");
    		}
    	});
    	$('.ui-actionsheet').removeClass('show');
    });
    
    $("#btn-dissolution-cancel").click(function(){
    	$('.ui-actionsheet').removeClass('show');
    });
});