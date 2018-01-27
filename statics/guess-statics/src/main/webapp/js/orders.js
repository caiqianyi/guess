$(function(){

	$("#btn-index").click(function(){
		go("/index.html");
	});
	
	$(".ui-icon-close").click(function(){
		$(this).parent().find("input[type=text]").val("");
	});
	
    // dropload
	var offset = 0;
    $('.content').dropload({
        scrollArea : window,
        loadDownFn : function(me){
            // 拼接HTML
        	oauth2.ajax({
                type: 'POST',
                url: '/guess/order/pastRecords',
                data:{offset:offset,size:10},
                dataType: 'json',
                success: function(response){
                    var arrLen = response.data.datas.length;
                    var html = "";
                    if(arrLen > 0){
                    	for(var i=0;i<response.data.datas.length;i++){
                    		var item = response.data.datas[i];
                    		var cl = item.status == 0 ? "" : item.status == -1 ? "color:red;" : "color:blue;";
                    		var result = item.status == 0 ? "未揭晓" : item.status == -1 ? "失败" : "正确";
                    		html += '<li class="ui-border-t">';
                    		html += '<h4>问题：'+item.subject+'</h4>';
                    		html += '<p><span>玩法：</span><span>'+item.kindOf+'</span></p>';
                    		html += '<p><span>期号：</span><span>'+item.expect+'</span></p>';
                    		html += '<p><span>回答：</span><span>'+item.optionName+'</span></p>';
                    		html += '<p><span>投入：</span><span>'+item.diamond+'</span></p>';
                    		html += '<p><span>产出：</span><span>'+item.score/100+'</span></p>';
                    		html += '<p><span>结果：</span><span style="'+cl+'">'+result+'</span></p>';
                    		html += '<p><span>时间：</span><span>'+new Date(item.createTime).format("yyyy-MM-dd hh:mm:ss")+'</span></p>';
                    		html += '</li>';
                    	}
                    	offset += arrLen;
                    // 如果没有数据
                    }else{
                        // 锁定
                        me.lock();
                        // 无数据
                        me.noData();
                    }
                    
                    // 为了测试，延迟1秒加载
                    // 插入数据到页面，放到最后面
                    $('.ui-list').append(html);
                    // 每次数据插入，必须重置
                    me.resetload();
                }
            });
        }
    });
});