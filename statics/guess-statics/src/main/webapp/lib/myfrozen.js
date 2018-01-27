var loading = {
	show:function(text){
		text = !text ? "正在加载中..." : text;
		var html = '<div class="ui-loading-block">';
		    html += '<div class="ui-loading-cnt">';
		    html += '<i class="ui-loading-bright"></i>';
		    html += '<p>'+text+'</p>';
		    html += '</div>';
		    html += '</div>';
		var block = $(".ui-loading-block");
		if(block.length == 0){
			block = $(html);
			$("body").append(block);
		}
		block.addClass('show');
	},
	close:function(){
		$(".ui-loading-block").remove();
	}
};