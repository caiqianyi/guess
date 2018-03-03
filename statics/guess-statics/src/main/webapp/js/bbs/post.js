$(function(){
	$(".PasteList").dropload({
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
	});
	
	$(".detail-bottom").click(function(){
    	go("/bbs/paste.html");
    });
	
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
});