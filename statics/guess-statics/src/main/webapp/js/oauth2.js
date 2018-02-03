$(function(){
	
	var openid = GetQueryString("openid");
	
	if(!openid){
		openid = 'oI9MD0mwrw-V2nt94K-uk7X5q2Hw';
	}
	oauth2.logout();
	window.storage.set('wxopenid',openid,false);
	go("/index.html");
});