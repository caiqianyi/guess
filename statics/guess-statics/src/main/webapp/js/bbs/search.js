$(function(){
	
	var kw = GetQueryString("kw");
	
    $("#search-close").click(function(){
		$(this).parent().find("input[type=search]").val("");
	});
    
    var offset = 0,size = 10;
    $("input[name=kw]").keyup(function(){
    	var kw = $(this).val();
    	if(kw && kw.length > 0){
    		$("#search-close").show();
    	}else{
    		$("#search-close").hide();
    	}
    });
    
    if(kw && kw.length > 0){
    	$("input[name=kw]").val(kw);
    	$("#result-datas").dropload({
            scrollArea : window,
            autoLoad:true,
            distance:50,	//拉动距离
            threshold:50,//提前加载距离
            domDown : {
               //上拉加载时的提示文字
                domClass   : 'dropload-down',
                domRefresh : '<div class="ui-loading-wrap"><p>上拉加载更多</p></div>',
                domLoad    : '<div class="ui-loading-wrap"><p>加载中，请稍候...</p><i class="ui-loading"></i></div>',
                domNoData  : '<div class="ui-loading-wrap"><p>暂无数据</p></div>'
            },
            loadDownFn : function(me){
            	oauth2.ajax({
        			url: "/bbs/theme/search",
        			data: {
        				kw : kw,
        				size: size,
        				offset: offset
        			},
        			success : function(datas){
        				var data = datas.data;
        				var html = "";
        				if(data.length > 0){
        					offset += data.length;
        					console.info($(".ui-list",$("#result-datas")).length);
        					var isFirst = $(".ui-list",$("#result-datas")).length == 0;
        					if(isFirst){
        						html += '<ul class="ui-list ui-border-tb">';
        					}
        					for(var i=0;i<data.length;i++){
        						html += '<li data-fn="'+data[i].kw+'" class="ui-border-t post">';
        						html += '	<div class="ui-avatar">';
        						html += '		<span style="background-image:url('+data[i].logo+')"></span>';
        						html += '	</div>';
        						html += '	<div class="ui-list-info">';
        						html += '		<h4 class="ui-nowrap">'+data[i].kw+'</h4>';
        						html += '		<p class="ui-nowrap">关注 '+data[i].subscribes+' 帖子 '+data[i].pasteCount+'</p>';
        						html += '	</div>';
        						html += '</li>';
        					}
        					if(isFirst){
        						html += '</ul>';
        					}
        					if(isFirst){
        						$("#result-datas").html(html);
        					}else{
        						$(".ui-list",$("#result-datas")).append(html);
        					}
        					$(".post").click(function(){
        				    	var bar = $(this).attr("data-fn");
        				    	go("/bbs/post.html?kw="+bar);
        				    });
        					if(data.length != size){
        						me.lock();
        						me.noData();
        					}
        				}else{
        					if(offset == 0){
        						html += '<div class="ui-btn-wrap">';
        						html += '	<p style="font-size: 16px;color: #828385;text-align: center;">尚未找到贴吧‘'+kw+'’，欢迎创建此吧</p>';
        						html += '</div>';
        						html += '<div class="ui-btn-wrap">';
        						html += '	<button class="ui-btn-lg ui-btn-primary" data-fn="'+kw+'" id="btn-create-bar">创建此吧</button>';
        						html += '</div>';
        						$("#result-datas").html(html);
        						
        						$("#btn-create-bar").click(function(){
        							go("/bbs/create_bar.html?kw="+$(this).attr("data-fn"));
        						});
        						// 锁定
                                me.lock();
        						me.noData();
        					}
        				}
        				me.resetload();
        			}
        		});
            }
    	});
    }
    
    $("input[name=kw]").keyup();
    
});