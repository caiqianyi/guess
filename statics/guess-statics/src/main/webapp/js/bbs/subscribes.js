$(function(){
	
	$('.ui-searchbar').tap(function(){
        go("/bbs/search.html");
    });
	
    $("#btn-tab-recommend").tap(function(){
    	go("/bbs/recommend.html");
    });
    
    $("#j_mysubscriber_edit").click(function(){
		if($(".ui-icon-close","#subscriber-list").length>0){
			if($(this).text() == '编辑'){
				$(".ui-icon-close","#subscriber-list").show();
				$(this).html("完成");
			}else{
				$(".ui-icon-close","#subscriber-list").hide();
				$(this).html("编辑");
			}
		}
	});
    
    function drawSubscriber(datas){
    	var html = '';
    	if(datas && datas.length>0){
    		for(var i=0;i<datas.length;i++){
    			var bar = datas[i];
    			html += '<li class="ui-border-t post" data-fn="'+bar.kw+'">';
    			html += '	<div class="ui-avatar">';
    			html += '   		<span style="background-image:url('+bar.logo+')"></span>';
    			html += '	</div>';
    			html += '	<div class="ui-list-info">';
    			html += '   	<h4 class="ui-nowrap">'+bar.kw+'</h4>';
    			html += '		<p class="ui-nowrap">关注 '+bar.subscribes+' 帖子 '+bar.pasteCount+'</p>';
    			html += '	</div>';
    			html += '	<i class="ui-icon-close" style="display: none" data-fn="'+bar.id+'"></i>';
    			html += '</li>';
    		}
    	}else{
    		html = '<li>请您先关注些感兴趣的贴吧！</li>';
    	}
    	$("#subscriber-list").html(html);
		
		$(".ui-icon-close","#subscriber-list").click(function(){
			var _this = this;
			var themeId = $(_this).attr("data-fn");
			oauth2.ajax({
		    	url: "/bbs/theme/unsubscribe",
		    	data: {
		    		themeId: themeId
		    	},
		    	success: function (response){
		    		$(_this).parent().remove();
		    	}
		    });
		});
		
		$(".post","#subscriber-list").click(function(){
			if($("#j_mysubscriber_edit").text() == '编辑'){
				var bar = $(this).attr("data-fn");
				go("/bbs/post.html?kw="+bar);
			}
	    });
    }
    
    function drawRecommend(datas){
		var html = '';
		if(datas && datas.length>0){
			for(var i=0;i<datas.length;i++){
				var bar = datas[i];
				html += '<li>';
				html += '	<div data-fn="'+bar.kw+'" class="post">';
				html += '		<div class="r-bar-jumbotron"><img class="r-img" src="'+bar.logo+'"></div>';
				html += '		<div class="r-bar-name">'+bar.kw+'</div>';
				html += '		<h4 style="font-size: 14px;">关注 '+bar.subscribes+'</h4>';
				html += '		<h4 style="font-size: 14px;">帖子 '+bar.pasteCount+'</h4>';
				html += '	</div>';
				html += '	<button class="ui-btn-s ui-btn-progress btn-subscriber" data-fn="'+bar.id+'" >关注</button>';
				html += '</li>';
			}
		}
		$(".r-recommend-list").html(html);
		
		$(".btn-subscriber").click(function(){
			var _this = this;
			var themeId = $(_this).attr("data-fn");
			var text = $(_this).text();
			if(text){
				var url  = "/bbs/theme/unsubscribe";
				if(text=='关注'){
					url = "/bbs/theme/subscribe";
				}
				oauth2.ajax({
			    	url: url,
			    	data: {
			    		themeId: themeId
			    	},
			    	success: function (response){
			    		if(text=='关注'){
			    			$(_this).html('已关注');
			    		}else{
			    			$(_this).html('关注');
			    		}
			    		var len = 0;
			    		$(".btn-subscriber").each(function(){
			    			if($(this).text() == '已关注'){
			    				len ++;
			    			}
			    		});
			    		if(len == 6){
			    			$("#btn-change-recommend").click();
			    		}
			    		mySubscriber();
			    	}
			    });
			}
		});
		
		$(".post",".r-recommend-list").click(function(){
	    	var bar = $(this).attr("data-fn");
	    	go("/bbs/post.html?kw="+bar);
	    });
    }
    
    function mySubscriber(flag){
    	oauth2.ajax({
        	url: "/bbs/theme/findByUserSubscriber",
        	success: function (response){
        		drawSubscriber(response.data);
        		if(flag){
        			$("#btn-change-recommend").click();
        		}
        	}
        });
    }
    
    $("#btn-change-recommend").click(function(){
    	oauth2.ajax({
	    	url: "/bbs/theme/findByRecommend",
	    	data: {
	    		size: 6
	    	},
	    	success: function (response){
	    		drawRecommend(response.data);
	    	}
	    });
    });
    
    mySubscriber(1);
    
});