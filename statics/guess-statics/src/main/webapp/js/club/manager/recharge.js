$(function(){
	
	$("#btn-index").click(function(){
		go("/index.html");
	});
	
    var clubId = GetQueryString("clubId");
    
    function refresh(){
    	oauth2.ajax({
    		type: 'GET',
    		url: '/guess/club/info',
    		data: {clubId:clubId},
    		dataType: 'json',
    		success: function(response){
    			var club = response.data;
    			if(!club){
    				alert("俱乐部不存在！");
    				go("/myclub.html");
    				return;
    			}
    			$("#info-balance").html(club.cardNum);
    		}
    	});
    }
    
    refresh();
	
    $(".ui-icon-close").click(function(){
		$(this).parent().find("input[type=text]").val("");
		$("input[name=chargeNum]").change();
	});
    
    $(".item").click(function(){
    	var isSelected = $(this).hasClass("ui-tag-selected");
    	$(".item").removeClass("ui-tag-selected");
    	if(!isSelected){
    		$(this).addClass("ui-tag-selected");
    		$("input[name=chargeNum]").val($(this).html());
    	}
    });
    
    $("input[name=chargeNum]").change(function(){
    	$(".item").removeClass("ui-tag-selected");
    });
    
    $("input[name=chargeNum]").change();
    
    $("#btn-recharge").click(function(){
    	var data = {};
    	data['clubId'] = clubId;
    	data['number'] = $("input[name=chargeNum]").val();
    	oauth2.ajax({
    		type: 'GET',
    		url: '/guess/club/recharge',
    		data: data,
    		dataType: 'json',
    		success: function(response){
    			refresh();
    			$.tips({
    	            content:'充卡成功！',
    	            stayTime:2000,
    	            type:"success"
    	        });
    		}
    	});
    });
    
});