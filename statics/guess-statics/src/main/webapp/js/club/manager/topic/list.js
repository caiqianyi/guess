
var tab = null;
$(function(){

	$("#btn-index").click(function(){
		go("/index.html");
	});
	
	$(".ui-icon-close").click(function(){
		$(this).parent().find("input[type=text]").val("");
	});
	
	var clubId = GetQueryString("clubId");
	
	oauth2.ajax({
		type: 'GET',
		url: '/guess/template/findByClubId/'+clubId,
		dataType: 'json',
		success: function(response){
			var datas = response.data;
			var html_e = "", html_d = "";
			for(var i=0;i<datas.length;i++){
				var templates = datas[i];
				console.info(datas[i]);
				var html = '<div class="ui-form-item ui-form-item-link ui-border-b" onclick="javascript:go(\'/club/manager/topic/edit.html?clubId='+clubId+'&id='+templates.id+'\');"><a href="javascript:void(0)">'+templates.subject+'（'+templates.topicType+'）</a></div>';
				if(templates.status == 1){
					html_e += html
				}else{
					html_d += html;
				}
			}
			$("#enabled_list").html(html_e);
			$("#disable_list").html(html_d);
		}
	});
	
	tab = new fz.Scroll('.ui-tab', {
        role: 'tab',
        autoplay: false
    });
	
	$("#btn-create").click(function(){
		go("/club/manager/topic/edit.html?clubId="+clubId);
	})
	
});