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
			$("input[name=name]").val(club.name);
			$("textarea[name=notice]").val(club.notice ? club.notice : "");
			$("input[name=maxMember]").val(club.maxMember);
		}
	});
	
    $(".ui-icon-close").click(function(){
		$(this).parent().find("input[type=text]").val("");
	});
    
    $("#btn-save").click(function(){
    	var data = {};
    	data['clubId'] = clubId;
    	data['name'] = $("input[name=name]").val();
    	data['notice'] = $("textarea[name=notice]").val();
    	data['maxMember'] = $("input[name=maxMember]").val();
    	oauth2.ajax({
    		type: 'GET',
    		url: '/guess/club/modify',
    		data: data,
    		dataType: 'json',
    		success: function(response){
    			club = response.data;
    			if(!club){
    				alert("俱乐部不存在！");
    				go("/myclub.html");
    				return;
    			}
    			alert("修改成功");
    			go("/club/manager/info.html?clubId="+clubId);
    		}
    	});
    });
    
});