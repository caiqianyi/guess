$(function(){
	var options = [];
	function addOption(option){
		var i = options.length;
		if(option){
			options[options.length] = option;
		}else{
			options[options.length] = [];
		}
		console.info(i);
		var html = "";
		html += '<div>';
		if(option){
			html += '<input type="hidden" name="options['+i+'].id" value="'+option.id+'">';
		}
		html += '<div class="ui-form-item ui-border-b demo-desc">答题选项-'+(i+1)+'<a href="javascript:void(0);" class="ui-icon-close" id="remove_item_'+i+'"></a></div>';
		html += '<div class="ui-form ui-border-t">';
		html += '    <div class="ui-form-item ui-border-b">';
		html += '        <label>选项名</label>';
		html += '        <input type="text" placeholder="如：01" name="options['+i+'].name"';
		if(option){
			html += ' value="'+option.name+'"';
		}
		html += '>';
		html += '        <a href="javascript:void(0);" class="ui-icon-close"></a>';
		html += '    </div>';
		html += '    <div class="ui-form-item ui-border-b">';
		html += '        <label>判断公式</label>';
		html += '        <input type="text" placeholder="如：#N1=1" name="options['+i+'].formula"';
		if(option){
			html += ' value="'+option.formula+'"';
		}
		html += '>';
		html += '        <a href="javascript:void(0);" class="ui-icon-close"></a>';
		html += '    </div>';
		html += '    <div class="ui-form-item ui-border-b">';
		html += '        <label>奖金值</label>';
		html += '        <input type="text" placeholder="如：2"  name="options['+i+'].odds"';
		if(option){
			html += ' value="'+option.odds+'"';
		}
		html += '>';
		html += '        <a href="javascript:void(0);" class="ui-icon-close"></a>';
		html += '    </div>';
		html += '    <div class="ui-form-item ui-border-b">';
		html += '        <label>顺序数字</label>';
		html += '        <input type="text" placeholder="数字越小排名越在前" name="options['+i+'].orderBy"';
		if(option){
			html += ' value="'+option.orderBy+'"';
		}
		html += '>';
		html += '        <a href="javascript:void(0);" class="ui-icon-close"></a>';
		html += '    </div>';
		html += '</div>';
		html += '</div>';
		$("#optionsDesc").append(html);
		
		$(".ui-icon-close").click(function(){
			$(this).parent().find("input[type=text]").val("");
		});
		
		$("[id^=remove_item_]").click(function(){
			var index = $(this).attr("id").replace("remove_item_");
			options.splice(index,1);
			$(this).parent().parent().remove();
		});
	}

	$("#btn-index").click(function(){
		go("/index.html");
	});
	
	$(".ui-icon-close").click(function(){
		$(this).parent().find("input[type=text]").val("");
	});
	
	var clubId = GetQueryString("clubId"),
		id = GetQueryString("id");
	
	$("#btn-addOption").click(function(){
		addOption();
	});
	
	if(id){
		oauth2.ajax({
			type: 'GET',
			url: '/guess/template/findById/'+id,
			dataType: 'json',
			success: function(response){
				var datas = response.data;
				$("input[name=subject]").val(datas.subject);
				$("input[name=topicType]").val(datas.topicType);
				$("input[name=orderBy]").val(datas.orderBy);
				if(datas.status == 1)
					$("input[name=status]").attr("checked","checked");
				else{
					$("input[name=status]").removeAttr("checked");
				}
				for(var i=0;i<datas.options.length;i++){
					var option = datas.options[i];
					addOption(option);
				}
				
			}
		});
	}else{
		$("#btn-addOption").click();
	}
	
	$("#btn-save").click(function(){
		var data = {};
		data["id"] = id;
		data["clubId"] = clubId;
		data["subject"] = $("input[name=subject]").val();
		data["topicType"] = $("input[name=topicType]").val();
		data["orderBy"] = $("input[name=orderBy]").val();
		data["status"] = $("input[name=status]").is(":checked") ? 1 : 0;
		for(var i=0;i<options.length;i++){
			data["options["+i+"].id"] = $("input[name='options["+i+"].id'").val();
			data["options["+i+"].name"] = $("input[name='options["+i+"].name'").val();
			data["options["+i+"].formula"] = $("input[name='options["+i+"].formula'").val();
			data["options["+i+"].odds"] = $("input[name='options["+i+"].odds'").val();
			data["options["+i+"].orderBy"] = $("input[name='options["+i+"].orderBy'").val();
		}
		oauth2.ajax({
			type: 'POST',
			url: '/guess/template/update/',
			data: data,
			dataType: 'json',
			success: function(response){
				alert("保存成功！");
				go("/club/manager/topic/list.html?clubId="+clubId);
			}
		});
	})
	
	$("#remove-topic").click(function(){
		oauth2.ajax({
			type: 'GET',
			url: '/guess/template/deleteBy/'+id+'/',
			dataType: 'json',
			success: function(response){
				$('.ui-actionsheet').removeClass('show');
				alert("删除成功！");
				go("/club/manager/topic/list.html?clubId="+clubId);
			}
		});
	})
	$("#btn-remove").click(function(){
		$(".ui-actionsheet").addClass("show");
	});
});