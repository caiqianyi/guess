function authAjax(param){
	var authAjax = {
		beforeSend: function(request) {
			var token = AccessToken.get();
	        request.setRequestHeader("Authorization",token);
		},
		success: function(response) {
			if(response.errcode > 0){
				Message.alert(response.errmsg);
				if(response.errcode == 10003 && flag){
		            window.location.href = '/login.html';
			    }
				return;
			}
			if(param.callback 
					&& typeof param.callback == 'function'){
				param.callback(response);
			}
		}
	};
	$.extend(authAjax,param);
	$.ajax(authAjax);
}
var AccessToken = {
	saveKey : "guess.oauth2.access_token",
	save : function (token,openid){
		window.sessionStorage.setItem(AccessToken.saveKey,token);
		if(openid){
			return window.localStorage.setItem("guess.oauth2.user.openid",openid);
		}
		return token;
	},
	get : function(){
		return window.sessionStorage.getItem(AccessToken.saveKey);
	},
	getOpenid : function(){
		return window.localStorage.getItem("guess.oauth2.user.openid");
	},
	clear : function(){
		window.sessionStorage.clear();
		window.localStorage.clear();
		return true;
	},
	refresh : function(){
		var openid = AccessToken.getOpenid();
		if(openid && openid.length > 0){
			$.ajax({
				url: "/oauth2/token/refresh",
				method: "GET",
				data: {openid : openid},
				dataType: "json",
				success: function(response){
					if(response.errcode == 0 &&
							response.data.access_token){
						var token = response.data["access_token"];
						AccessToken.save(token);
					}
				}
			})
		}
	}
};