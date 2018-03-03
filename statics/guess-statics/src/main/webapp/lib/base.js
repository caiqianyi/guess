var sys_version = '20180124';
function go(url){
	if(typeof static_version == "undefined"){
		static_version = sys_version;
	}
	var go = url + (url.indexOf("?")>-1 ? "&":"?") + "v="+static_version;
	window.location.href = go;
}

function include(i){
	if(i >= importFiles.length){
		return;
	}
	if(typeof static_version == "undefined"){
		static_version = sys_version;
	}
	var head = document.getElementsByTagName('head')[0];
	var file = importFiles[i];
	var item = null;
	if(file.type == 'css'){
		item = document.createElement('link');
		item.href = file.src+'?v='+static_version;
		item.rel = 'stylesheet';
	    item.type = 'text/css';
	}
	if(file.type == 'script'){
		item = document.createElement('script');
		item.setAttribute('type', 'text/javascript'); 
		item.setAttribute('src', file.src+'?v='+static_version);
		item.setAttribute('charset', 'UTF-8');
	}
	if(item){
		head.appendChild(item);
		item.addEventListener('load', function () {
			include(++i);
		}, false);
	}
}

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

//获取
function GetQueryString(name){	
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");	
    var r = window.location.search.substr(1).match(reg);	
    if (r != null) return unescape(r[2]);	
    return null;
}

var importFiles = [
	{src:"/css/frozen.css",type:"css"},
	{src:"/css/basic.css",type:"css"},
	{src:"/lib/zepto.min.js",type:"script"},
	{src:"/lib/zepto.animate.slide.js",type:"script"},
	{src:"/lib/frozen.js",type:"script"},
	{src:"/lib/storge.js",type:"script"},
	{src:"/lib/myfrozen.js",type:"script"},
	{src:"/lib/oauth2.js",type:"script"},
];

window.onload = function(){  
	include(0);
}  
