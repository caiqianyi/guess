$(function(){
	
	$(".ui-icon-close").click(function(){
		$(this).parent().find("input[type=text]").val("");
	});
	
	$("#btn-index").click(function(){
		go("/index.html");
	});
	
	$("#btn-create-toggle").click(function(){
		go("/club/create.html");
	});
	
	$("#btn-join-toggle").click(function(){
		$("#join-dialog").dialog("show");
	});
	
	$("#btn-join-action").click(function(){
		var clubId = $("input[name=clubId]").val();
		oauth2.ajax({
			flag: 1,//自定义异常处理
            type: 'GET',
            url: '/guess/club/applyJoin',
            data: {clubId:clubId},
            dataType: 'json',
            success: function(response){
            	var content = response.errmsg;
            	if(response.errcode == 0){
            		content = '申请成功，等待创建人确认！';
            	}
            	el3=$.tips({
                    content:content,
                    stayTime:3000,
                    type:"warn"
                })
                el3.on("tips:hide",function(){
                    console.log("tips hide");
                })
            }
		});
	});
	
	var tab = new fz.Scroll('.ui-tab', {
        role: 'tab',
        autoplay: false
    });
	
    /* 滑动开始前 */
    $("#btn-createByClubs").click(function(){
    	oauth2.ajax({
            type: 'GET',
            url: '/guess/club/myCreateBy',
            dataType: 'json',
            success: function(response){
                var arrLen = response.data.length;
                var html = "";
                if(arrLen > 0){
                	for(var i=0;i<response.data.length;i++){
                		var item = response.data[i];
                		html += '<li>';
                		html += '	<div class="ui-grid-halve-img ui-tag-svip" onclick="go(\'/club/manager/index.html?clubId='+item.clubId+'\');">';
                		if(item.icon){
                			html += '		<span style="background-image: url(http://placeholder.qiniudn.com/290x160)"></span>';
                		}else{
                			html += '		<span style="background-image: url(/img/club/'+item.kindOf+'.jpg)"></span>';
                		}
                		html += '	</div>';
                		html += '	<p>'+item.name+'</p>';
                		html += '</li>';
                		console.info(item);
                	}
                	$('#createClubs').html(html);
                }else{
                	//$("#btn-joinByClubs").click();
                }
            }
        });
    });
    
    $("#btn-joinByClubs").click(function(){
    	oauth2.ajax({
            type: 'GET',
            url: '/guess/club/myJoinBy',
            dataType: 'json',
            success: function(response){
                var arrLen = response.data.length;
                var html = "";
                if(arrLen > 0){
                	for(var i=0;i<response.data.length;i++){
                		var item = response.data[i];
                		html += '<li>';
                		html += '	<div class="ui-grid-halve-img ui-tag-svip" onclick="go(\'/club/'+item.kindOf+'.html?clubId='+item.clubId+'\');">';
                		if(item.icon){
                			html += '		<span style="background-image: url(http://placeholder.qiniudn.com/290x160)"></span>';
                		}else{
                			html += '		<span style="background-image: url(/img/club/'+item.kindOf+'.jpg)"></span>';
                		}
                		html += '	</div>';
                		html += '	<p>'+item.name+'</p>';
                		html += '</li>';
                	}
                	$('#joinClubs').html(html);
                }
            }
        });
    });
    
    $("#btn-createByClubs").click();
    
});