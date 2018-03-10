$(function(){
	
	var pasteId = GetQueryString("pasteId"),
		postId = GetQueryString("postId"),
		kw = GetQueryString("kw"),
	    tab = GetQueryString("tab");
	
	if(!tab){
		tab = "publishTime";
	}
	
	$(".ui-tab-nav li").removeClass("current");
	$("#tab-"+tab).addClass("current");
	
	$(".ui-tab-nav li").click(function(){
		var id = $(this).attr("id").substring(4);
		if(tab != id){
			go("/bbs/replys.html?pasteId="+pasteId+"&postId="+postId+"&kw="+kw+"&tab="+id);
		}
	});
	
	$("#btn-toback").click(function(){
		go("/bbs/paste.html?id="+pasteId+"&kw="+kw);
	})
	
	function draw1(data){
		var html = '';
		html += '<div class="list_item_top clearfix">';
		html += '	<div class="list_item_top_avatar">';
		html += '		<a href=""><span><img src="'+data.headimgurl+'" alt="头像" width="36" height="36" class="user_img"></span></a>';
		html += '	</div>';
		html += '	<div class="list_item_top_name">';
		html += '		<span class="user_name red_name">'+data.nickName+'</span>';
		html += '		<br>';
		html += '		<span class="list_item_pipe"></span><span class="list_item_time">'+new Date(data.createTime).format("MM-dd hh:mm")+'</span>';
		html += '	</div>';
		html += '	<div class="content" lz="'+(data.isCreator?1:0)+'" tid="'+data.id+'">'+data.content;
		var pictures = data.pictures;
		if(pictures){
			var imgs = pictures.split(",");
			if(data.content && data.content.length > 0){
				html += '<br/>';
			}
			for(var j=0;j<imgs.length;j++){
				html += '<img class="BDE_Image" src="'+imgs[j]+'"></span>';
			}
		}
		html += '	</div>';
		html += '	<div style="    padding-bottom: 10px;font-size: 16px;color: #7CAE23;">共'+data.replyCount+'条回复</div>';
		html += '</div>';
		$(".list_main").html(html);
	}
	var offset = 0, size = 5;
	oauth2.ajax({
		url: "/bbs/paste/post/findById",
		data: {
			pasteId: pasteId,
			id: postId
		},
		success: function(response){
			$("#page_title").text(response.data.tier+"楼");
			$("#praiseCount").text(response.data.praiseCount+"赞");
			draw1(response.data);
			
			$(".PasteList").dropload({
	            scrollArea : window,
	            autoLoad:true,
	            distance:50,	//拉动距离
	            threshold:50,//提前加载距离
	            domDown : {
	               //上拉加载时的提示文字
	                domClass   : 'dropload-down',
	                domRefresh : '<div class="ui-loading-wrap"><p>上拉加载更多</p></div>',
	                domLoad    : '<div class="ui-loading-wrap"><p>加载中，请稍候...</p><i class="ui-loading"></i></div>',
	                domNoData  : '<div class="ui-loading-wrap"><p>暂无更多回复</p></div>'
	            },
	            loadDownFn : function(me){
	            	var flag = 0;
	            	if(tab == "replyCount"){
	            		flag = 1;
	            	}
		        	var data = {
	        			pasteId: pasteId,
	        			postId: postId,
	        			flag: flag,
	    				size: size,
	    				offset: offset
	    			};
		        	if(tab == 'tab-highlight'){
		        		data.highlight = 'highlight';
		        	}
		        	oauth2.ajax({
		    			url: "/bbs/paste/replay/findByPasteId",
		    			data: data,
		    			success: function(response){
		    				var data = response.data;
		    				var isFirst = offset === 0;
		    				console.info(data);
		    				var replyHtml = '';
		    				if(data.length > 0){
		    					offset += data.length;
		    					
		    					for(var i=0;i<data.length;i++){
		    						var reply = data[i];
		    						replyHtml += '<li class="feeds-li feeds-li-normal" id="reply_'+reply.id+'">';
		    						replyHtml += '	<div class="detail-top">';
		    						replyHtml += '		<div class="item-user">';
		    						replyHtml += '			<div class="item-user-wrap">';
		    						replyHtml += '				<div class="item-user-avator">';
		    						replyHtml += '					<img class="avator-img img-ph" src="'+reply.headimgurl+'">';
		    						replyHtml += '				</div>';
		    						replyHtml += '				<span class="item-user-text">';
		    						replyHtml += '					<span class="item-user-name">'+reply.nickName+'</span>';
		    						replyHtml += '					<span class=""></span>';
		    						replyHtml += '				</span>';
		    						replyHtml += '				<span class="item-user-time">'+new Date(reply.createTime).showAgo()+'</span><i class="icon-cf-close"></i>';
		    						replyHtml += '				<span class="comment-zan"><i class="'+(reply.praise ? 'ui-icon-liked' : 'ui-icon-like')+' font24"></i> <i class="comment-dz-num">'+reply.praiseCount+'</i></span>';
		    						replyHtml += '			</div>';
		    						replyHtml += '		</div>';
		    						replyHtml += '	</div>';
		    						replyHtml += '	<div class="detail-bottom">';
		    						replyHtml += '		<div class="report-content comment-con-bot">';
		    						replyHtml += '				<p class="groupbrief feed-two-line">'+reply.content+'</p>';
		    						replyHtml += '		</div>';
		    						replyHtml += '	</div>';
		    						replyHtml += '</li>';
		    					}
		    					
		    					var o = null;
		    					if(isFirst){
			    					o = $(".post-list").html(replyHtml);
			    				}else{
			    					o = $(".post-list").append(replyHtml);
			    				}
		    					$(".comment-zan",o).click(function(){
		    						var _this = this;
		    						var replyId = $(this).parents(".feeds-li").attr("id").substring(6);
		    						if($("i",_this).hasClass("ui-icon-like")){
		    							oauth2.ajax({
		    								url: "/bbs/praise/addLog/REPLY",
		    								data: {
		    									pasteId: pasteId,
		    									recordId: replyId
		    								},
		    								success: function(response){
		    									$(".font24",_this).attr("class","ui-icon-liked font24");
		    									$(".comment-dz-num",_this).text(parseInt($(".comment-dz-num",_this).text())+1);
		    								}
		    							});
		    						}else{
		    							oauth2.ajax({
		    								url: "/bbs/praise/cancel/REPLY",
		    								data: {
		    									pasteId: pasteId,
		    									recordId: replyId
		    								},
		    								success: function(response){
		    									$(".font24",_this).attr("class","ui-icon-like font24");
		    									$(".comment-dz-num",_this).text(parseInt($(".comment-dz-num",_this).text())-1);
		    								}
		    							});
		    						}
		    					});
		    					
		    					$(".detail-bottom",o).click(function(){
		    						var replyId = $(this).parents(".feeds-li").attr("id").substring(6);
		    						$("input[name=replyId]","#layer_reply_container").val(replyId);
		    						//var toReplyUserId = $("input[name=toReplyUserId]","#layer_reply_container").val();
		    						$("#pb_layer_reply").animate({ left: 0 }, 200, null, function(){
		    							$("#main").hide();
		    						});
		    					});
		    					
		    					if(data.length != size){
		    						// 锁定
			                        me.lock();
			    					me.noData();
		    					}
		    				}else{
		    					
		    					// 锁定
		                        me.lock();
		    					me.noData();
		    				}
		    				me.resetload();
		    			}
		    		});
		        	
		        }
			});
			
			//$(".l-level").text(bar.score);
		}
	})
	
	$("textarea").keydown(function(){
		var scrollTop = $(this).scrollTop();
		if(scrollTop && scrollTop>0){
			$(this).height($(this).height() + scrollTop);
		}
	});
	
	$("#btn-to-reply").click(function(){
		$("#pb_layer_reply").animate({ left: 0 }, 200, null, function(){
			$("#main").hide();
		});
	});
	
	$("#btn-layer-reply").click(function(){
		var content = $("textarea[name=content]","#layer_reply_container").val();
		var replyId = $("input[name=replyId]","#layer_reply_container").val();
		
		if(!content || content.length < 1){
			$.tips({
                content:'内容不能为空',
                stayTime:1000,
                type:"info"
            });
			return;
		}
		
		if(content.length > 700){
			$.tips({
                content:'输入最大为700个汉字',
                stayTime:1000,
                type:"info"
            });
			return;
		}
		
		oauth2.ajax({
        	url: "/bbs/paste/reply/publish",
        	type: 'POST',
        	data: {
        		content: content,
        		pasteId: pasteId,
        		postId: postId,
        		toReplyId: replyId
        	},
        	dataType: "json",
        	success: function(response){
        		loading.close();
        		$("input[name=layerId]","#layer_reply_container").val("");
        		$("input[name=replyId]","#layer_reply_container").val("");
        		go("/bbs/replys.html?pasteId="+pasteId+"&postId="+postId+"&kw="+kw);
        	},
        	error: function(){
        		loading.close();
        	}
        });
	});
	
});