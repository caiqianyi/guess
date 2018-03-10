var dropload;
$(function(){
	
	var kw = GetQueryString("kw"),
	    tab = GetQueryString("tab");
	
	if(!tab){
		tab = "tab-new";
	}
	
	$(".ui-tab-nav li").removeClass("current");
	$("#"+tab).addClass("current");
	
	$(".ui-tab-nav li").click(function(){
		var id = $(this).attr("id");
		if(tab != id){
			go("/bbs/post.html?kw="+kw+"&tab="+id);
		}
	});
	
	$("#btn-tohome").click(function(){
		go("/bbs/subscribes.html");
	})
	
	/*$(".PasteList").dropload({
        scrollArea : window,
        autoLoad:true,
        distance:50,	//拉动距离
        threshold:50,//提前加载距离
        domUp : {
        	domClass : 'dropload-up',
        	domRefresh : '<div class="ui-loading-wrap"><p>↓下拉刷新</p></div>',
        	domUpdate  : '<div class="ui-loading-wrap"><p>↑释放更新</p></div>',
        	domLoad : '<div class="ui-loading-wrap"><p>加载中，请稍候...</p><i class="ui-loading"></i></div>'
        },
        domDown : {
           //上拉加载时的提示文字
            domClass   : 'dropload-down',
            domRefresh : '<div class="ui-loading-wrap"><p>上拉加载更多</p></div>',
            domLoad    : '<div class="ui-loading-wrap"><p>加载中，请稍候...</p><i class="ui-loading"></i></div>',
            domNoData  : '<div class="ui-loading-wrap"><p>暂无数据</p></div>'
        },
        loadUpFn : function(me){
        	console.info("loadUpFn");
           //请求的数据，可通过接口同步或异步加载
            // 无数据
            setTimeout(function(){
            	me.resetload();
            },1000);
        },
        loadDownFn : function(me){
        	console.info("loadDownFn");
            setTimeout(function(){
            	me.resetload();
            },1000);
        }
	});*/
	var bar = null, offset = 0;
	oauth2.ajax({
		url: "/bbs/theme/findByKw",
		data: {
			kw: kw
		},
		success: function(response){
			bar = response.data;
			$(".name-info .name").text(bar.kw+"吧");
			$("#barName").text(bar.kw+"吧");
			$("img[class=logo]").attr("src",bar.logo);
			$(".info-num .subscribes").text(bar.subscribes);
			$(".info-num .pasteCount").text(bar.pasteCount);
			
			dropload = $("#main").dropload({
	            scrollArea : window,
	            autoLoad:true,
	            distance:50,	//拉动距离
	            threshold:50,//提前加载距离
	            domDown : {
	               //上拉加载时的提示文字
	                domClass   : 'dropload-down',
	                domRefresh : '<div class="ui-loading-wrap"><p>上拉加载更多</p></div>',
	                domLoad    : '<div class="ui-loading-wrap"><p>加载中，请稍候...</p><i class="ui-loading"></i></div>',
	                domNoData  : '<div class="ui-loading-wrap"><p>没有更多了</p></div>'
	            },
	            loadDownFn : function(me){
		        	var data = {
	    				plateId: bar.id,
	    				size: 3,
	    				offset: offset
	    			};
		        	if(tab == 'tab-highlight'){
		        		data.highlight = 'highlight';
		        	}
		        	oauth2.ajax({
		    			url: "/bbs/paste/findByPlateId",
		    			data: data,
		    			success: function(response){
		    				var data = response.data;
		    				var isFirst = offset === 0;
		    				console.info(data);
		    				var topHtml = '', postHtml = '';
		    				if(data.length > 0){
		    					offset += data.length;
		    					
		    					for(var i=0;i<data.length;i++){
		    						var post = data[i];
		    						if(post.top){
		    							topHtml += '<li class="tl_top scale-1px-bottom">';
		    							topHtml += '	<a href="javascript:go(\'/bbs/paste.html?id='+post.id+'&kw='+kw+'\');"';
		    							topHtml += '		class="j_common ti_item no_border" tid="5071225635">';
		    							topHtml += '		<div class="ti_title">';
		    							topHtml += '			<span class="ti_title_icon ti_icon_zhiding">置顶</span> <span>'+post.title+'</span>';
		    							topHtml += '		</div>';
		    							topHtml += '	</a>';
		    							topHtml += '</li>';
		    						}else{
		    							postHtml += '<li class="feeds-li feeds-li-normal">';
		    							postHtml += '	<div class="detail-top">';
		    							postHtml += '		<div class="item-user">';
		    							postHtml += '			<div class="item-user-wrap">';
		    							postHtml += '				<div class="item-user-avator">';
		    							postHtml += '					<img class="avator-img img-ph"';
		    							postHtml += '						src="'+post.headimgurl+'">';
		    							postHtml += '				</div>';
		    							postHtml += '				<span class="item-user-text"><span';
		    							postHtml += '					class="item-user-name">'+post.nickName+'</span><span class=""></span></span><span';
		    							postHtml += '					class="item-user-time">'+new Date(post.createTime).showAgo()+'</span><i class="icon-cf-close"></i>';
		    							postHtml += '			</div>';
		    							postHtml += '		</div>';
		    							postHtml += '	</div>';
		    							postHtml += '	<div class="detail-bottom" id="post_'+post.id+'">';
		    							postHtml += '		<div class="paste">';
		    							postHtml += '			<div class="report-content">';
		    							postHtml += '				<p class="groupbrief feed-two-line">'+post.content+'</p>';
		    							postHtml += '			</div>';
		    							var pictures = post.pictures;
		    							if(pictures){
		    								postHtml += '		<div class="img-wrap clearfix">';
		    								var imgs = pictures.split(",");
		    								for(var j=0;j<imgs.length;j++){
		    									postHtml += '			<div class="feed-img img-ph"';
		    									postHtml += '				style="width: 130px; height: 130px;';
		    									if(j<2){
		    										postHtml += '					margin-right: 2px;';
		    									}
		    									postHtml += '			">';
		    									postHtml += '				<img src="'+imgs[j]+'" class="" style="margin-top: 0px; width: 173.333px; height: 130px;">';
		    									postHtml += '			</div>';
		    									if(j==2){
		    										break;
		    									}
		    								}
		    								
		    								postHtml += '		</div>';
		    							}
		    							postHtml += '		</div>';
		    							postHtml += '		<div class="post-bottom-v3">';
		    							postHtml += '			<div class="post-btn-v3 btn-like">';
		    							postHtml += '				<i class="'+(post.praise ? 'ui-icon-liked' : 'ui-icon-like')+'" style="display: inline-block;"></i>';
		    							postHtml += '				<h4 style="display: inline-block;">'+post.praiseCount+'</h4>';
		    							postHtml += '			</div>';
		    							postHtml += '			<div class="post-btn-v3 btn-comment">';
		    							postHtml += '				<i class="ui-icon-comment"></i>';
		    							postHtml += '				<h4>'+post.replyPasteCount+'</h4>';
		    							postHtml += '			</div>';
		    							postHtml += '			<div class="post-btn-v3 btn-share">';
		    							postHtml += '				<i class="ui-icon-share"></i>';
		    							postHtml += '			</div>';
		    							postHtml += '		</div>';
		    							postHtml += '	</div>';
		    							postHtml += '</li>';
		    						}
		    					}
		    					
		    					var o = null;
		    					if(isFirst){
			    					$("#topList").html(topHtml);
			    					o = $(".post-list").html(postHtml);
			    				}else{
			    					o = $(".post-list").append(postHtml);
			    				}
		    					$(".btn-like",o).click(function(){
		    						var _this = this;
		    						var postId = $(this).parents(".detail-bottom").attr("id").substring(5);
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
		    					
		    					$(".paste",o).click(function(){
		    						var postId = $(this).parents(".detail-bottom").attr("id").substring(5);
		    						go("/bbs/paste.html?id="+postId+"&kw="+kw);
		    					});
		    					
		    					$(".btn-comment",o).click(function(){
		    						var postId = $(this).parents(".detail-bottom").attr("id").substring(5);
		    						go("/bbs/paste.html?id="+postId+"&kw="+kw);
		    					});
		    					
		    					$(".btn-share",o).click(function(){
		    						var postId = $(this).parents(".detail-bottom").attr("id").substring(5);
		    						go("/bbs/paste.html?id="+postId+"&kw="+kw+"&action=share");
		    					});
		    					
		    					if(data.length != 3){
		    						// 锁定
			                        me.lock();
			    					me.noData();
		    					}
		    				}else{
		    					
		    					if(isFirst){
			    					$("#topList").html(topHtml);
			    					$(".post-list").html(postHtml);
			    				}
		    					
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
	
	$("#btn-publish").click(function(){
		$("#pulishPost").animate({ left: 0 }, 200, null, function(){
			$("#main").hide();
		});
	});
	
	$("#btn-publish-return").click(function(){
		$("#main").show(function(){
			$("#main").removeAttr("style");
			$("#pulishPost").animate({ left: "100%" }, 200, null, function(){
				console.info(2);
			});
		});
	});
	
	
	$("textarea").keydown(function(){
		var scrollTop = $(this).scrollTop();
		if(scrollTop && scrollTop>0){
			$(this).height($(this).height() + scrollTop);
		}
	})
	;
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
			
			$(".m-repic-close",item).click(function(){
				$(this).parent().parent().remove();
			});
        });
    });
	
	function postPublish(data){
		oauth2.ajax({
        	url: "/bbs/paste/publish",
        	type: 'POST',
        	data: data,
        	dataType: "json",
        	success: function(response){
        		loading.close();
        		$("textarea[name=content]").val("");
        		$("input[name=title]").val("");
        		$(".compose-image").remove();
        		go("/bbs/paste.html?id="+response.data.id+"&kw"+kw);
        	},
        	error: function(){
        		loading.close();
        	}
        });
	}
	
	$("#btn-post-publish").click(function(){
		var content = $("textarea[name=content]").val(),
			title = $("input[name=title]").val();
		
		if(!title || title.length == 0){
			$.tips({
                content:'请给帖子起个标题吧！',
                stayTime:1000,
                type:"info"
            });
			return;
		}
		if(title.length < 4){
			$.tips({
                content:'标题字数至少4个汉字！',
                stayTime:1000,
                type:"info"
            });
			return;
		}
		if(!content || content.length < 10){
			$.tips({
                content:'输入至少10个汉字',
                stayTime:1000,
                type:"info"
            });
			return;
		}
		
		if(!content || content.length > 700){
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
	            		namespace : "bbs/post/"+bar.id,
	            		fileType : fileType,
	            		imgStr : base64
	            	},
	            	dataType: "json",
	            	success: function(response){
	            		loading.close();
	            		console.info(response.data.uri);
	            		uploadSuccess++;
	            		pictures += response.data.uri;
	            		if(uploadSuccess == uploadSize){
	            			postPublish({
	            				title: title,
	            				content: content,
	            				pictures: pictures,
	            				plateId: bar.id
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
			postPublish({
				title: title,
				content: content,
				plateId: bar.id
			});
		}
	});
});