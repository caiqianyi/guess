var memId = null;
function delMember(memberId){
	memId = memberId;
	$("#remove-actionsheet").addClass("show");
}

function joinAudit(memberId){
	memId = memberId;
	$("#join-actionsheet").addClass("show");
}

function quitAudit(memberId){
	memId = memberId;
	$("#quit-actionsheet").addClass("show");
}

$(function(){
    var clubId = GetQueryString("clubId");
    
    $("#btn-index").click(function(){
		go("/index.html");
	});
	
	$("#members1").click(function(){
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
    			html += '    <tr><th>头像</th><th>昵称</th><th>ID</th><th>聊天状态</th><th>操作</th></tr>';
				html += '    </thead>';
				html += '     <tbody>';
				
				for(var i=0;i<response.data.length;i++){
					var member = response.data[i];
					console.info(member);
					html += '    <tr>';
					html += '		<td><div style="width: 100%;display: -webkit-box;-webkit-box-orient: vertical;-webkit-box-pack: center;-webkit-box-align: center;text-align: center;"><div class="ui-avatar-s"><span style="background-image:url('+member.headimgurl+')"></span></div></div></td>';
					html += '		<td>'+member.nickname+'</td>';
					html += '    	<td>'+member.userId+'</td>';
					html += '    	<td>'+(member.online?'在线':'下线')+'</td>';
					if(member.flag == 0){
						html += '    	<td></td>';
					}else{
						html += '    	<td><a href="javascript:delMember('+member.id+');">编辑</a></td>';
					}
					html += '    </tr>';
				}
				html += '    </tbody>';
				html += ' </table>';
				$("#tab-members1").html(html);
			}
		});
	});
	
	$("#members0").click(function(){
		oauth2.ajax({
			type: 'GET',
			url: '/guess/club/members',
			data: {
				clubId:clubId,
				status:0,
			},
			dataType: 'json',
			success: function(response){
				var html = "";
	    		html += '<table class="ui-table ui-border">';
	    		html += '    <thead>';
    			html += '    <tr><th>头像</th><th>昵称</th><th>ID</th><th>操作</th></tr>';
				html += '    </thead>';
				html += '     <tbody>';
				
				for(var i=0;i<response.data.length;i++){
					var member = response.data[i];
					console.info(member);
					html += '    <tr>';
					html += '		<td><div style="width: 100%;display: -webkit-box;-webkit-box-orient: vertical;-webkit-box-pack: center;-webkit-box-align: center;text-align: center;"><div class="ui-avatar-s"><span style="background-image:url('+member.headimgurl+')"></span></div></div></td>';
					html += '		<td>'+member.nickname+'</td>';
					html += '    	<td>'+member.userId+'</td>';
					html += '    	<td><a href="javascript:joinAudit('+member.id+');">审核</a></td>';
					html += '    </tr>';
				}
				html += '    </tbody>';
				html += ' </table>';
				$("#tab-members0").html(html);
			}
		});
	});
	$("#members-1").click(function(){
		oauth2.ajax({
			type: 'GET',
			url: '/guess/club/members',
			data: {
				clubId:clubId,
				status:-1,
			},
			dataType: 'json',
			success: function(response){
				var html = "";
	    		html += '<table class="ui-table ui-border">';
	    		html += '    <thead>';
    			html += '    <tr><th>头像</th><th>昵称</th><th>ID</th><th>操作</th></tr>';
				html += '    </thead>';
				html += '     <tbody>';
				
				for(var i=0;i<response.data.length;i++){
					var member = response.data[i];
					console.info(member);
					html += '    <tr>';
					html += '		<td><div style="width: 100%;display: -webkit-box;-webkit-box-orient: vertical;-webkit-box-pack: center;-webkit-box-align: center;text-align: center;"><div class="ui-avatar-s"><span style="background-image:url('+member.headimgurl+')"></span></div></div></td>';
					html += '		<td>'+member.nickname+'</td>';
					html += '    	<td>'+member.userId+'</td>';
					html += '    	<td><a href="javascript:quitAudit('+member.id+');">审核</a></td>';
					html += '    </tr>';
				}
				html += '    </tbody>';
				html += ' </table>';
				$("#tab-members-1").html(html);
			}
		});
	});
	
	$("#btn-remove-confirm").click(function(){
		oauth2.ajax({
			type: 'GET',
			url: '/guess/club/remove/memeber',
			data: {
				clubId:clubId,
				memberId:memId,
			},
			dataType: 'json',
			success: function(response){
				$("#remove-actionsheet").removeClass("show");
				$("#members1").click();
				alert("操作成功");
			}
		});
	});
	
	function joinPass(memberId,agree){
		oauth2.ajax({
			type: 'GET',
			url: '/guess/club/approvalJoin',
			data: {
				clubId:clubId,
				memberId:memberId,
				agree: agree
			},
			dataType: 'json',
			success: function(response){
				$("#join-actionsheet").removeClass("show");
				$("#members0").click();
				alert("操作成功");
			}
		});
	}
	
	function quitPass(memberId,agree){
		oauth2.ajax({
			type: 'GET',
			url: '/guess/club/approvalLeave',
			data: {
				clubId:clubId,
				memberId:memberId,
				agree: agree
			},
			dataType: 'json',
			success: function(response){
				$("#quit-actionsheet").removeClass("show");
				$("#members-1").click();
				alert("操作成功");
			}
		});
	}
	
	$("#btn-join-pass").click(function(){
		joinPass(memId,1);
	});
	
	$("#btn-join-nopass").click(function(){
		joinPass(memId,0);
	});
	
	$("#btn-quit-pass").click(function(){
		quitPass(memId,1);
	});
	
	$("#btn-quit-nopass").click(function(){
		quitPass(memId,0);
	});
	
	$("#members1").click();
	
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
    
    var tab = new fz.Scroll('.ui-tab', {
        role: 'tab',
        autoplay: false
    });
    
    /* 滑动开始前 */
    tab.on('beforeScrollStart', function(fromIndex, toIndex) {
        //console.log(fromIndex,toIndex);// from 为当前页，to 为下一页
    });
});