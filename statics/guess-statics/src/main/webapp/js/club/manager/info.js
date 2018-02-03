$(function(){
	
	$("#btn-index").click(function(){
		go("/index.html");
	});
	
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
    		if(club.icon){
    			$(".ui-avatar-lg").html('<span style="background-image: url(http://placeholder.qiniudn.com/290x160)"></span>');
    		}else{
    			$(".ui-avatar-lg").html('<span style="background-image: url(/img/club/'+club.kindOf+'.jpg)"></span>');
    		}
    		$("#info-name").html(club.name);
    		$("#info-id").html(club.clubId);
    		$("#info-balance").html(club.cardNum);
    		$("#info-kindOf").html(club.kindOf == 'jclq' ? '竞猜篮球' : '北京赛车');
    		$("#info-notice").html(club.notice);
    		$("#info-currentMember").html(club.currentMember);
    		$("#info-maxMember").html(club.maxMember);
    		$("#info-totalLiveness").html(club.totalLiveness);
		}
	});
	
    $(".ui-icon-close").click(function(){
		$(this).parent().find("input[type=text]").val("");
	});
    
    $("[id^=btn-toEdit]").click(function(){
    	go("/club/manager/edit.html?clubId="+clubId);
    });
    $("#btn-toMembers").click(function(){
    	go("/club/manager/members.html?clubId="+clubId);
    });
    $("#btn-toLiveness").click(function(){
    	go("/club/manager/liveness.html?clubId="+clubId);
    });
    $("#btn-toRecharge").click(function(){
    	go("/club/manager/recharge.html?clubId="+clubId);
    });
});