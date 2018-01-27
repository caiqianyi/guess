$(function(){

	$("#btn-index").click(function(){
		go("/index.html");
	});
	
	$(".ui-icon-close").click(function(){
		$(this).parent().find("input[type=text]").val("");
	});
	
	$("#btn-index").click(function(){
		go("/index.html");
	});
	
	$("#btn-create").click(function(){
		var name = $("input[name=name]").val(),
		kindOf = $("select[name=kindOf]").val(),
		notice = $("textarea[name=notice]").val()
		maxMember = $("input[name=maxMember]").val()
		;
		oauth2.ajax({
			flag: 1,//自定义错误提示
	    	url: "/guess/club/create",
	    	data: {
	    		name:name,
	    		kindOf:kindOf,
	    		cardNum:200,
	    		notice:notice,
	    		maxMember:maxMember
	    	},
	    	success: function(response){
	    		var data = response.data;
	    		if(response.errcode == 0){
	    			alert("创建成功!");
	    			go("/myclub.html");
	    		}else{
	    			var html = '<div class="ui-tooltips ui-tooltips-warn">';
	    			html += '<div class="ui-tooltips-cnt ui-tooltips-cnt-link ui-border-b">';
	    			html += '<i></i>'+response.errmsg;
	    			html += '</div>';
	    			html += '</div>';
	    			$("#errtips").html(html);
	    		}
	    	}
	    });
	});
});