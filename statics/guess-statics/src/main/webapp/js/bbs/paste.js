$(function(){
	
	var kw = GetQueryString("kw"),
    	tab = GetQueryString("tab"),
		id = GetQueryString("id");

	if(!tab){
		tab = "tab-first";
	}
	
	$("#barName").text(kw+"吧");
	
	$(".ui-tab-nav li").removeClass("current");
	$("#"+tab).addClass("current");
	
	$(".ui-tab-nav li").click(function(){
		var t = $(this).attr("id");
			go("/bbs/paste.html?id="+id+"&kw="+kw+"&tab="+t);
	});
	
	$("#btn-back").click(function(){
		if(kw){
			go("/bbs/post.html?kw="+kw);
			return;
		}
		history.back();
	});
	
	function drawPost(data,flag){
		var html = "";
		html += '<li class="list_item post_list_item default_feedback j_post_list_item no_border">';
		html += '	<div class="list_item_wrapper">';
		html += '		<div class="list_main">';
		if(!flag){
			html += '			<div class="post_title_embed">'+data.title+'</div>';
		}
		html += '			<div class="list_item_top clearfix">';
		html += '				<div class="list_item_top_avatar">';
		html += '					<a href=""><span><img src="'+data.headimgurl+'" alt="头像" width="36" height="36" class="user_img"></span></a>';
		html += '				</div>';
		html += '				<div class="list_item_top_name">';
		html += '					<span class="list_item_floor_num">第1楼</span>';
		html += '					<span class="list_item_pipe"></span><span class="list_item_time">'+new Date(data.createTime).format("MM-dd hh:mm")+'</span>';
		html += '				</div>';
		html += '				<div class="content" lz="1">'+data.content;
		var pictures = data.pictures;
		if(pictures){
			var imgs = pictures.split(",");
			html += '<br/>';
			for(var j=0;j<imgs.length;j++){
				html += '			<img class="BDE_Image" src="'+imgs[j]+'"></span>';
			}
		}
		html += '				</div>';
		html += '			</div>';
		html += '			<div class="post-rcmdlables"><div class="lable-item">'+kw+'</div></div>';
		html += '			<div class="demo-block">';
		html += '	            <ul class="ui-grid-halve" style="padding-left: 80px;padding-right: 80px;">';
		html += '	                <li><div class="post-actions" style="line-height: 44px;text-align: center;"><span class="read-num">阅读</span> <span class="num">'+data.scanCount+'</span></span></div></li>';
		html += '	                <li>';
		html += '	                	<div style="text-align: center;" id="post_'+data.id+'" class="btn-like">';
		html += '							<i class="'+(data.praise ? 'ui-icon-liked' : 'ui-icon-like')+'" style="display: inline-block;"></i>';
		html += '							<h4 style="display: inline-block;">'+data.praiseCount+'</h4>';
		html += '						</div>';
		html += '	                </li>';
		html += '	            </ul>';
		html += '	        </div>';
		html += '		</div>';
		html += '	</div>';
		html += '</li>';
		var o = $(html);
		$("#pblist").append(o);
		$(".btn-like",o).click(function(){
			var _this = this;
			var postId = $(this).attr("id").substring(5);
			if($("i",_this).hasClass("ui-icon-like")){
				oauth2.ajax({
					url: "/bbs/praise/addLog/PASTE",
					data: {
						pasteId: postId,
						recordId: postId
					},
					success: function(response){
						$("i",_this).attr("class","ui-icon-liked");
						$("h4",_this).text(parseInt($("h4",_this).text())+1);
					}
				});
			}else{
				oauth2.ajax({
					url: "/bbs/praise/cancel/PASTE",
					data: {
						pasteId: postId,
						recordId: postId
					},
					success: function(response){
						$("i",_this).attr("class","ui-icon-like");
						$("h4",_this).text(parseInt($("h4",_this).text())-1);
					}
				});
			}
		});
	}
	
	function drawPaste(datas){
		if(datas && datas.length){
			var html = '';
			for(var i=0;i<datas.length;i++){
				var data = datas[i];
				html += '<li tid="'+data.id+'" class="list_item post_list_item default_feedback j_post_list_item"';
				html += '	<div class="list_item_wrapper">';
				html += '		<div class="list_main">';
				html += '			<div class="list_item_top clearfix">';
				html += '				<div class="list_item_top_avatar">';
				html += '					<a href=""><span><img src="'+data.headimgurl+'" alt="头像" width="36" height="36" class="user_img"></span></a>';
				html += '				</div>';
				html += '				<div class="list_item_top_name">';
				html += '					<span class="list_item_floor_num">第'+data.tier+'楼</span>';
				html += '					<span class="list_item_pipe"></span><span class="list_item_time">'+new Date(data.createTime).format("MM-dd hh:mm")+'</span>';
				html += '					<span style="text-align: center;    float: right;" class="btn-like" id="post_'+data.id+'"><i class="'+(data.praise ? 'ui-icon-liked' : 'ui-icon-like')+'" style="display: inline-block;"></i><h4 style="display: inline-block;">'+data.praiseCount+'</h4>						</div>';
				html += '				</div>';
				html += '				<div class="content" lz="'+(data.isCreator?1:0)+'" tid="'+data.id+'">'+data.content;
				var pictures = data.pictures;
				if(pictures){
					var imgs = pictures.split(",");
					if(data.content && data.content.length > 0){
						html += '<br/>';
					}
					for(var j=0;j<imgs.length;j++){
						html += '			<img class="BDE_Image" src="'+imgs[j]+'"></span>';
					}
				}
				html += '				</div>';
				if(data.replyCount > 0){
					html += '				<div class="fr_list 2 j_floor_panel"><a href="javascript:go(\'/bbs/replys.html?pasteId='+id+'&postId='+data.id+'&kw='+kw+'\');" class="pb_floow_load j_fload_more fload_more_btn">查看全部'+data.replyCount+'条评论</a></div>';
				}
				html += '			</div>';
				html += '		</div>';
				html += '	</div>';
				html += '</li>';
			}
			var i = $(html);
			$("#pblist").append(i);
			$(".btn-like",i).click(function(){
				var _this = this;
				var postId = $(this).attr("id").substring(5);
				if($("i",_this).hasClass("ui-icon-like")){
					oauth2.ajax({
						url: "/bbs/praise/addLog/POST",
						data: {
							pasteId: id,
							recordId: postId
						},
						success: function(response){
							$("i",_this).attr("class","ui-icon-liked");
							$("h4",_this).text(parseInt($("h4",_this).text())+1);
						}
					});
				}else{
					oauth2.ajax({
						url: "/bbs/praise/cancel/POST",
						data: {
							pasteId: id,
							recordId: postId
						},
						success: function(response){
							$("i",_this).attr("class","ui-icon-like");
							$("h4",_this).text(parseInt($("h4",_this).text())-1);
						}
					});
				}
			});
			$(".content",i).click(function(){
				var tid = $(this).attr("tid");
				$("input[name=layerId]","#layer_reply_container").val(tid);
				//var toReplyUserId = $("input[name=toReplyUserId]","#layer_reply_container").val();
				$("#pb_layer_reply").animate({ left: 0 }, 200, null, function(){
					$("#main").hide();
				});
			});
		}
			
	}
	
	function loadMainPost(call,flag){
		oauth2.ajax({
			url: "/bbs/paste/findById",
			data: {
				id: id
			},
			success: function(response){
				var post = response.data;
				drawPost(post,flag);
				
				if(call){
					call();
				}
			}
		});
	}
	
	function myDropload(over){
		dropload = $("#main").dropload({
            scrollArea : window,
            autoLoad:true,
            distance:50,	//拉动距离
            threshold:50,//提前加载距离
            domDown : {
               //上拉加载时的提示文字
                domClass   : 'dropload-down',
                domRefresh : '<div class="ui-loading-wrap"><p>向上拉动</p></div>',
                domLoad    : '<div class="ui-loading-wrap"><p>加载中，请稍候...</p><i class="ui-loading"></i></div>',
                domNoData  : ''
            },
            loadDownFn : function(me){
            	var flag = 0;
            	if(tab == 'tab-lastnew'){
            		flag = 1;
            	}
            	if(tab == 'tab-owner'){
            		flag = 2;
            	}
	        	var data = {
        			pasteId: id,
        			flag: flag,
    				size: size,
    				offset: offset
    			};
	        	if(tab == 'tab-highlight'){
	        		data.highlight = 'highlight';
	        	}
	        	oauth2.ajax({
	    			url: "/bbs/paste/post/findByPasteId",
	    			data: data,
	    			success: function(response){
	    				var data = response.data;
	    				var isOver = false;
    					if(data.length != size){
    						isOver = true;
    					}
	    				if(isOver && over){
	    					over(me,response);
	    				}
	    			}
	    		});
	        	
	        }
		});
		
	}
	var offset = 0,size = 5;
	if(tab == 'tab-lastnew'){
		myDropload(function(me,response){
			var data = response.data;
			var isFirst = offset === 0;
			console.info(data);
			var isOver = false;
			if(data.length > 0){
				offset += data.length;
				
				drawPaste(data);
				if(data.length != size){
					isOver = true;
					// 锁定
                    me.lock();
					me.noData();
				}
			}else{
				isOver = true;
				// 锁定
                me.lock();
				me.noData();
			}
			
			if(isOver){
				loadMainPost(null,1)
			}
			me.resetload();
		});
	}else{
		loadMainPost(function(){
			myDropload(function(me,response){
				var data = response.data;
				var isFirst = offset === 0;
				console.info(data);
				var isOver = false;
				if(data.length > 0){
					offset += data.length;
					
					drawPaste(data);
					if(data.length != size){
						isOver = true;
						// 锁定
                        me.lock();
    					me.noData();
					}
				}else{
					isOver = true;
					// 锁定
                    me.lock();
					me.noData();
				}
				me.resetload();
			});
		});
	}

	$("#btn-toreply").click(function(){
		$("#pb_poster_layer").animate({ left: 0 }, 200, null, function(){
			$("#main").hide();
		});
	});
	
	$("#btn-layer-return").click(function(){
		$("#main").show(function(){
			$("#main").removeAttr("style");
			$("#pb_poster_layer").animate({ left: "100%" }, 200, null, function(){
				console.info(2);
			});
		});
	});
	
	$("#btn-reply-return").click(function(){
		$("#main").show(function(){
			$("#main").removeAttr("style");
			$("#pb_layer_reply").animate({ left: "100%" }, 200, null, function(){
				console.info(2);
			});
		});
	});
	
	$(".fload_more_btn").click(function(){
		go("/bbs/replys.html");
	});
	
	$("textarea").keydown(function(){
		var scrollTop = $(this).scrollTop();
		if(scrollTop && scrollTop>0){
			$(this).height($(this).height() + scrollTop);
		}
	});
	
	$("input[type=file]").change(function(){
		
    	lrz(this.files[0], {width: 280}, function (results) {
    		var fileType = results.origin.name.substring(results.origin.name.indexOf(".")+1);
    		var html = '<li>';
				html+= '	<div data-v-8edeee54="" draggable="true" class="m-box-center m-box-center-a image-wrap m-trans img3 none" style="transform: translate(0px, 0px); touch-action: none; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">';
				html+= '		<div data-v-8edeee54="" class="image-placeholder"></div> ';
				html+= '		<input type="hidden" value="'+results.base64+'" name="base64"/> ';
				html+= '		<img data-v-8edeee54="" src="'+results.blob+'" class="compose-image" data-fn="'+fileType+'" name="'+results.origin.name+'">';
				html+= '		<i class="ui-icon-close-page m-repic-close"></i>  ';
				html+= '	</div>';
				html+= '</li>';
			var item = $(html);
			item.insertBefore($("#addImg"));
			
			console.info($(".m-repic-close",item));
			$(".m-repic-close",item).click(function(){
				$(this).parent().parent().remove();
			});
        });
    });
	
	function pastePublish(data){
		oauth2.ajax({
        	url: "/bbs/paste/post/reply",
        	type: 'POST',
        	data: data,
        	dataType: "json",
        	success: function(response){
        		loading.close();
        		$("textarea[name=content]").val("");
        		$(".compose-image").remove();
        		go("/bbs/paste.html?id="+id+"&kw="+kw+"&tab=tab-lastnew");
        	},
        	error: function(){
        		loading.close();
        	}
        });
	}
	
	$("#btn-post-reply").click(function(){
		var content = $("textarea[name=content]","#poster_layer_container").val();
		var len = $(".compose-image","#poster_layer_container").length;
		if(len == 0 && (!content || content.length < 10)){
			$.tips({
                content:'输入至少10个汉字',
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
		loading.show("发布中，请稍后...");
		if($(".compose-image").length>0){
			var uploadSuccess = 0,uploadSize = $(".compose-image").length;
			var pictures = "";
			$(".compose-image").each(function(){
				console.info($(this).parent());
				var base64 = $("input[name=base64]",$(this).parent()).val();
				name = $(this).attr("name");
				var fileType = name.substring(name.indexOf(".")+1);
				oauth2.ajax({
	            	url: "/upload/img",
	            	type: 'POST',
	            	data: {
	            		namespace : "bbs/paste/"+id,
	            		fileType : fileType,
	            		imgStr : base64
	            	},
	            	dataType: "json",
	            	success: function(response){
	            		loading.close();
	            		uploadSuccess++;
	            		pictures += response.data.uri;
	            		if(uploadSuccess == uploadSize){
	            			pastePublish({
	            				content: content,
	            				pictures: pictures,
	            				pasteId: id
	            			});
	            		}else{
	            			pictures += ",";
	            		}
	            	},
	            	error: function(){
	            		loading.close();
	            	}
	            });
			});
		}else{
			pastePublish({
				content: content,
				pasteId: id
			});
		}
	});
	
	$("#btn-layer-reply").click(function(){
		var content = $("textarea[name=content]","#layer_reply_container").val();
		var layerId = $("input[name=layerId]","#layer_reply_container").val();
		var toReplyUserId = $("input[name=toReplyUserId]","#layer_reply_container").val();
		
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
        		pasteId: id,
        		postId: layerId,
        		toReplyId: toReplyUserId
        	},
        	dataType: "json",
        	success: function(response){
        		loading.close();
        		$("textarea[name=content]","#layer_reply_container").val("");
        		$("input[name=layerId]","#layer_reply_container").val("");
        		$("input[name=toReplyUserId]","#layer_reply_container").val("");
        		go("/bbs/paste.html?id="+id+"&kw="+kw);
        	},
        	error: function(){
        		loading.close();
        	}
        });
	});
});