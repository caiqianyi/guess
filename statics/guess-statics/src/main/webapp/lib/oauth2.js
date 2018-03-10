var oauth2 = {
    lock : false,
	wechatOAlogin : function (param,call){
		//登陆验证
		$.ajax({
			url: '/oauth2/token/wechatOA',
			data: param,
			beforeSend: function(request) {
				loading.show("正在登录中...");
		    },
			success: function(response){
				loading.close();
				if(!response){
					alert("服务器异常，稍后重试！");
					return;
				}
				if(response.errcode != 0){
					//验证不通过 
					alert(response.errmsg)
				}else{
					//如果验证通过
					var token = response.data["access_token"];
					window.storage.set('token',token,false);

					var date = new Date();
					date.setSeconds(date.getSeconds() + 7200);
					var loginTime = date.getTime();
					window.storage.set('loginTime',loginTime,false);
					
					var wxopenid = response.data["wxopenid"];
					if(wxopenid){
						window.storage.set('wxopenid',wxopenid,false);
					}
					var openid = response.data["openid"];
					if(openid){
						window.storage.set('openid',openid,false);
					}
					if(call && typeof call == "function")
						call(date);
				}
			},
			error: function(){
				loading.close();
			}
		});
	},
	login : function (param,call){
		//登陆验证
		$.ajax({
			url: '/oauth2/token/refresh',
			data: param,
			beforeSend: function(request) {
				loading.show("正在登录中...");
		    },
			success: function(response){
				loading.close();
				if(response.errcode != 0){
					//验证不通过 
					alert(response.errmsg)
				}else{
					//如果验证通过
					var token = response.data["access_token"];
					window.storage.set('token',token,false);

					var date = new Date();
					date.setSeconds(date.getSeconds() + 7200);
					var loginTime = date.getTime();
					window.storage.set('loginTime',loginTime,false);
					
					var wxopenid = response.data["wxopenid"];
					if(wxopenid){
						window.storage.set('wxopenid',wxopenid,false);
					}
					var openid = response.data["openid"];
					if(openid){
						window.storage.set('openid',openid,false);
					}
					if(call && typeof call == "function")
						call(date);
				}
			},
			error: function(){
				loading.close();
			}
		});
	},
	isLogin: function (){
		var token = window.storage.get('token'),
			loginTime = window.storage.get('loginTime');
		if(!token || token.length == 0 
				|| !loginTime || new Date().getTime() > loginTime){
			return false;
		}
		return true;
	},
	checkLogin: function(flag){
		if(oauth2.isLogin()){
			if(!flag){
				return true;
			}
			oauth2.logout();
			alert('您的账号在其它地方登录，如非您本人操作，请尽快修改密码！');
		}else{
			alert('登录超时，请重新登录!');
		}
		$.ajax({
			url: '/oauth2/wechat/snsapi_userinfo',
			data: {state:"login"},
			beforeSend: function(request) {
				loading.show("跳转中...");
		    },
			success: function(response){
				loading.close();
				if(response.errcode != 0){
					//验证不通过 
					alert(response.errmsg)
				}else{
					//如果验证通过
					window.location.href = response.data;
				}
			},
			error: function(){
				loading.close();
			}
		});
		return false;
	},
	autoLogin: function (){
		var openid = window.storage.get('openid',false);
		if(openid && !oauth2.isLogin()){
			oauth2.login({openid:openid,flag:"autoLogin"});
			return;
		}
		
		var wxopenid = window.storage.get('wxopenid',false);
		if(wxopenid && wxopenid.length > 0 && !oauth2.isLogin()){//是否有微信openid
			oauth2.wechatOAlogin({openid:wxopenid,flag:"autoLogin"});
		}
	},
	logout: function (){
		window.storage.remove('token',false);
		window.storage.remove('loginTime',false);
		window.storage.remove('openid',false);
		window.storage.remove('wxopenid',false);
		if(oauth2.isLogin()){
			$.ajax({
				url:"/account/logout",
				type: "GET",
				data:{},
				beforeSend: function(request) {
			        request.setRequestHeader("Authorization",window.storage.get('token'));
			    },
				success: function(data){
				}
			});
		}
	},
	ajax: function (param){
		var ap = $.extend({
				beforeSend: function(request) {
					request.setRequestHeader("Authorization",window.storage.get('token'));
				},
				dataType: "json",
				timeout : 30000
			},param);
		
		ap["success"] = function(response){
			if(response.errcode == 10003){
				oauth2.lock = true;
				var openid = window.storage.get('openid',false);
				if(openid && openid.length > 0){//是否 系统openid
					oauth2.login({openid:openid,flag:"autoLogin"},function(){
						oauth2.lock = false;
						oauth2.ajax(param);
					});
					return;
				}
				var wxopenid = window.storage.get('wxopenid',false);
				if(wxopenid && wxopenid.length > 0){//是否有微信openid
					oauth2.wechatOAlogin({openid:wxopenid,flag:"autoLogin"},function(){
						oauth2.lock = false;
						oauth2.ajax(param);
					});
					return;
				}
				oauth2.checkLogin(true);
				return;
			}
			if(response.errcode != 0 && !param.flag){
				alert(response.errmsg);
				return;
			}
			if(param.success && typeof param.success == "function")
				param.success(response);
		}
		if(!oauth2.lock)
			$.ajax(ap);
	}
};
