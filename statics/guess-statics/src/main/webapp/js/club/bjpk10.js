$(function(){
	
	$("#btn-index").click(function(){
		go("/index.html");
	});
	
	var isLateralNavAnimating = false;
	var member;
	$('.cd-nav-trigger').click(function(event) {
        event.preventDefault();
        //若动画正在进行，则停止 
        if (!isLateralNavAnimating) {
            if ($(this).parents('.csstransitions').length > 0)
                isLateralNavAnimating = true;

            $('body').toggleClass('navigation-is-open');
            $('.cd-navigation-wrapper').one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function() {
                //动画结束 
                isLateralNavAnimating = false;
            });
        }
    });
    
    var tab = new fz.Scroll('.ui-tab', {
        role: 'tab',
        autoplay: false
    });
    
    var clubId = GetQueryString("clubId");
    
    var webchat = new WebChat({
    	message: function(message){
    		if(message.action == 'broadcast'){//系统广播
    			var isSelf = message.datas.from.id == member.id;
    			var html = "";
    			if(isSelf){
    				html += '<li class="by-other">';
    				html += '	<div class="avatar pull-right">';
    				html += '  		<span class="ui-avatar-tiled"><span style="background-image:url('+message.datas.from.headimgurl+'"></span></span>';
    				html += '	</div>';

    				html += '	<div class="chat-content">';
    				html += '  		<div class="chat-meta">'+new Date(message.time).format("hh:mm:ss")+' <span class="pull-right">'+message.datas.from.nickname+'</span></div>';
    				html += '  			'+message.datas.content;
    				html += '  		<div class="clearfix"></div>';
    				html += '	</div>';
    				html += '</li>';
    				
    			}else{
    				html += '<li class="by-me">';
    				html += '	<div class="avatar pull-left">';
    				html += '  		<span class="ui-avatar-tiled"><span style="background-image:url('+message.datas.from.headimgurl+'"></span></span>';
    				html += '	</div>';

    				html += '	<div class="chat-content">';
    				html += '  		<div class="chat-meta">'+message.datas.from.nickname+' <span class="pull-right">'+new Date(message.time).format("hh:mm:ss")+'</span></div>';
    				html += '  			'+message.datas.content;
    				html += '  		<div class="clearfix"></div>';
    				html += '	</div>';
    				html += '</li>';
    			}
    			$("#chat").append(html);
    			$("#message").val("");  //清空输入区
    			var chat = $("#chat-view");
    			chat.scrollTop(chat[0].scrollHeight);   //让聊天区始终滚动到最下面
    		}
    	},
    	notice: function(message){
    		var html = "";
    		html += "<li><p style=\"text-align:center;color:red;\"> "+new Date(message.time).format("hh:mm:ss")+" ["+message.datas.nickname+"] ";
    		html += message.action == 'join' ? "进入房间": "退出房间";
    		html += "</p></li>";
    		$("#chat").append(html);
            var chat = $("#chat-view");
            chat.scrollTop(chat[0].scrollHeight);   //让聊天区始终滚动到最下面
    	},
    	online: function(members){
    		var html = "";
    		for(var memid in members){
    			var member = members[memid];
    			if(member.online)
    				html += '<span class="ui-avatar-tiled"><span style="background-image:url('+member.headimgurl+')"></span></span>';
    		}
    		$("#online").html(html);
    	}
    });
    
    $("#btn-publish").click(function(){
    	var content = $("input[name=content]").val();
    	if(webchat.send(null, content)){
    		$("input[name=content]").val("");
    	}else{
    		$.tips({
    	        content: "发送失败",
    	        stayTime: 2000,
    	        type: "warn"
    	    });
    	}
    });
    
    oauth2.ajax({
    	url: "/guess/club/into",
    	type: 'GET',
    	data: {clubId:clubId},
    	dataType: "json",
    	success: function(response){
    		member = response.data;
    		webchat.connection({
    			id:clubId,
    			memberId:member.id
    		});
    	}
    });
    
    oauth2.ajax({
		type: 'GET',
		url: '/guess/club/info',
		data: {clubId:clubId},
		dataType: 'json',
		success: function(response){
			var club = response.data;
			$("#info-name").html(club.name);
			$("marquee").html(club.notice);
		}
	});
    
    var topics = null;
    function refreshTopic(){
    	oauth2.ajax({
	    	url: "/guess/topic/current/"+clubId,
	    	type: 'POST',
	    	success: function(response){
	    		console.info(response.data);
	    		topics = response.data;
	    		if(topics.length > 0){
	    			var typeHtml = "";
	    			for(var i=0;i<topics.length;i++){
	    				var topic = topics[i];
	    				typeHtml += '<div class="ui-form-item ui-form-item-link ui-border-b" id="btn-type-'+i+'"><a href="javascript:void(0);">'+topic.topicType+'</a></div>';
	    			}
	    			$("#topicTypes").html(typeHtml);
	    			
	    			$("[id^=btn-type-]").click(function(){
	    				var i = $(this).attr("id").replace("btn-type-","");
	    				var topic = topics[i];
	    				$("#topicSubject").html(topic.subject);
	    				var optionHtml = '';
	    				for(i=0;i<topic.options.length;i++){
	    					var option = topic.options[i];
	    					if(i % 2 == 0){
	    						optionHtml += '<div class="demo-desc ui-footer-stable ui-btn-group ui-border-t">';
	    					}
	    					optionHtml += '<button class="ui-btn-lg" value="'+option.id+'">'+option.name+'</button>';
	    					if(i % 2 == 1){
	    						optionHtml += '</div>';
	    					}
	    				}
	    				$("#topicOption").html(optionHtml);
	    			});
	    			$("#btn-type-0").click();
	    			$(".ui-btn-lg").click(function(){
	    				if($(this).hasClass("ui-btn-primary")){
	    					$(this).removeClass("ui-btn-primary");
	    				} else {
	    					$(this).addClass("ui-btn-primary");
	    				}
	    			});
	    		}
	    	}
	    });
    }
    
    var itimer = new IssueTimer({
		kindOf : "bjpk10",
		onPullNewIssue : function(issue){
			$("#current-issue").html(issue.expect);
			refreshTopic();
		},
		onCounterPerSecond : function(date){
			$("#current-counter").html('距截至：<span class="red">'+date.minute+'</span>分<span class="red">'+date.second+'</span>秒');
		},
		onCounterOver : function(expect){
			console.info("onCounterOver.expect="+expect);
		},
		onRefCounterBefore : function(expect){
			$("#prev-issue").html(expect);
			$("#prev-counter").html('距开奖：<span class="red" id="kj-min">00</span>分<span class="red" id="kj-second">00</span>秒');
			$("#prev").show();
		},
		onRefCounterPerSecond : function(date){
			$("#prev-counter").html('距开奖：<span class="red" id="kj-min">'+date.minute+'</span>分<span class="red" id="kj-second">'+date.second+'</span>秒');
			$("#prev").show();
		},
		onRefCounterOver : function(){
			$("#prev-counter").html('开奖中...');
		},
		onRefreshData : function(datas,flag){
			var yl_datas = datas.yl_datas;
			if(!flag){
				$("#prev").hide();
				
				oauth2.ajax({
			    	url: "/guess/order/winning/"+clubId+"/bjpk10/"+yl_datas[yl_datas.length-1].expect,
			    	type: 'POST',
			    	success: function(response){
			    		if(response.data.length > 0){
			    			alert("恭喜您，上期参与答题正确！！");
			    		}
			    	}
				});
			}
    		var html = "";
    		for(var i=yl_datas.length-10;i<yl_datas.length;i++){
    			var item = yl_datas[i];
    			html += '<tr class="td-data"><td>'+item.expect+'</td>';
    			var lotterys = item.lottery.split(",");
    			for(var k in lotterys){
    				html += '<td>'+lotterys[k]+'</td>';
    			}
    			html+= "</tr>";
    		}
    		$(".td-data").remove();
    		$("#prev").before(html);
		}
	});
    
    itimer.open();
    
    $("#btn-answer").click(function(){
    	var diamond = $("input[name=diamond]").val();
    	var ubp = $(".ui-btn-primary");
    	if(ubp.length == 0){
    		alert("请先选择答案！");
    		return;
    	}
    	if(!diamond || isNaN(diamond)){
    		alert("请输入娱乐倍数");
    		return;
    	}
    	var options = '';
    	ubp.each(function(i){
    		options += $(this).val();
    		if(i< ubp.length -1){
    			options += ",";
    		}
    	});
    	oauth2.ajax({
        	url: "/guess/order/joinGuess/",
        	type: 'GET',
        	data: {optionId:options,diamond:diamond},
        	dataType: "json",
        	success: function(response){
        		alert("参与答题成功！");
        		console.info(response.data);
        	}
        });
    });
    
    $("#btn-reset").click(function(){
    	$("input[name=diamond]").val("");
    });
    
});