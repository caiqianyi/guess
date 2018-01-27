$(function(){
	
	$("#btn-index").click(function(){
		go("/index.html");
	});
	
    var clubId = GetQueryString("clubId");
	
	function reload(){
		oauth2.ajax({
			type: 'GET',
			url: '/guess/club/members',
			data: {
				clubId:clubId,
				status:1,
			},
			dataType: 'json',
			success: function(response){
	    		var html = "";
	    		html += '<table class="ui-table ui-border">';
	    		html += '    <thead>';
				html += '    <tr><th>头像</th><th>昵称</th><th>ID</th><th>总活跃度</th><th>待审核</th><th>查阅</th></tr>';
				html += '    </thead>';
				html += '     <tbody>';
				for(var i=0;i<response.data.length;i++){
					var member = response.data[i];
					console.info(member);
	            
					html += '    <tr>';
					html += '		<td><div style="width: 100%;display: -webkit-box;-webkit-box-orient: vertical;-webkit-box-pack: center;-webkit-box-align: center;text-align: center;"><div class="ui-avatar-s"><span style="background-image:url('+member.headimgurl+')"></span></div></div></td>';
					html += '		<td>'+member.nickname+'</td>';
					html += '    	<td>'+member.userId+'</td>';
					html += '    	<td>'+member.totalLiveness+'</td>';
					html += '    	<td>'+member.unauditedLiveness+'</td>';
					if(member.unauditedLiveness == 0){
						html += '    	<td></td>';
					}else{
						html += '    	<td><label class="ui-checkbox"><input type="checkbox" checked="" value="'+member.id+'"></label></td>';
					}
					html += '    </tr>';
				}
				html += '    </tbody>';
				html += ' </table>';
				$("#tab-members1").html(html);
			}
		});
	}
	
	reload();
	
	$("#checkLivenessToggle").click(function(){
		if($("#tab-members1 input[type=checkbox]:checked").length ==0){
			alert("请选中查阅记录！");
			return;
		}
		$(".ui-actionsheet").addClass("show");
	});
	
	$("#checkLivenessConfirm").click(function(){
		var mems = [];
		$("#tab-members1 input[type=checkbox]:checked").each(function(){
			mems[mems.length] = $(this).val();
		});
		oauth2.ajax({
			type: 'GET',
			url: '/guess/club/checkLiveness',
			data: {
				clubId: clubId,
				memberId: mems.toString(),
			},
			dataType: 'json',
			success: function(response){
				$('.ui-actionsheet').removeClass('show');
				reload();
			}
		});
		
	});
	
    $(".ui-icon-close").click(function(){
		$($(this).parent().find("input[type=text]")[0]).val("");
	});
    
    $('.ui-searchbar').tap(function(){
        $('.ui-searchbar-wrap').addClass('focus');
        $('.ui-searchbar-input input').focus();
    });
    
    $('.ui-searchbar-cancel').tap(function(){
        $('.ui-searchbar-wrap').removeClass('focus');
    });
    
});