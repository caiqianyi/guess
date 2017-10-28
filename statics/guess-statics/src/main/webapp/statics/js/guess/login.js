//点击登陆function
function loginMethod(){
	//获取账号，密码，验证码
	var username = $("#username").val();
	var psw = $("#password").val();
	var verifycode = $("#chkcode").val();

	if(username == '' || psw == '' || verifycode == '') {
		//均不能为空
		alert('账号、密码和验证码不能为空!');
		//改变验证码
		$("#codeImg").click();
		return false;
	}else{
		var usernamePsw = username + ',' + psw;
		//加密账号密码
		$.ajax({
			type:'get',
			url: '/common/encrypt?encrypts='+usernamePsw,
			success: function(data){
				if(data.errmsg == 'ok'){
					username = data.data[0];
					psw = data.data[1];
					//设置登陆需要传入的参数值
					var param = {};
					param.username = username;
					param.password = psw;
					param.verfiyCode = verifycode;
					//登陆验证
					$.ajax({
						url: '/oauth2/token/refresh',
						data: param,
						success: function(data){
							if(data.errcode != 0){
								//验证不通过
								alert(data.errmsg)
								$("#password").val('');
								$("#chkcode").val('');
								//改变验证码
								$("#codeImg").click();
							}else{
								
								$('form').fadeOut(500);
								$('.wrapper').addClass('form-success');
								
								//如果验证通过
								var token = data.data["access_token"];
								var openid = data.data["openid"];
								var date = new Date();
								date.setTime(date.getTime()+2*3600*1000);
								AccessToken.save(token,openid);
								setTimeout(function(){
									$(window).attr('location','/index.html');
								},700)										
							}
						} 
					});	

				}
			} 
		});							
	}
}

$(function(){
	//点击登录
	$('#login-button').click(function(event) {
		event.preventDefault();
		//验证登录
		loginMethod();
	});
	
	//点击改变验证码
	$("#codeImg").click(function(){
		$(this).attr('src','/images/captcha?t='+Math.random());
	})
});
