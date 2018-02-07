function WebChat(event){
	this.pullServer();
	this.event = $.extend(this.event,event)
};

WebChat.prototype = {
	ws: null,
	discovery: [],
	event:{
		logout: function(){},
		message: function(){},
		notice: function(){},
		online: function(){}
	}
};

WebChat.prototype.pullServer = function(){
	var _this = this;
	loading.show("更新服务器...");
	oauth2.ajax({
		url: '/guess/club/webchat/discovery',
		timeout : 10000,
		type: "GET",
		success: function(response){
			loading.close();
			_this.discovery = response.data;
		},
		error: function(){
			loading.close();
		}
	});
};

WebChat.prototype.nextWs = function(discovery){
	if(discovery.length < 2){
		return discovery.length -1;
	}
	var maxNum = discovery.length -1, minNum = 0;
	var i = parseInt(Math.random()*(maxNum-minNum+1)+minNum,10);
	return i;
}

WebChat.prototype.connection = function(room,discovery){
	var _this = this;
	if(_this.ws){
		_this.close();
	}
	if(!discovery){
		discovery = _this.discovery.concat();
	}
	
	var inde = _this.nextWs(discovery);
	
	if(inde == -1){
		var el=$.tips({
            content:'服务器升级维护中',
            stayTime:2000,
            type:"warn"
        });
        el.on("tips:hide",function(){
        });
		return;
	}
	
	var ws_url  = discovery[inde];
	_this.ws = new WebSocket(ws_url+room.id+"/"+room.memberId); //创建WebSocket对象
	_this.ws.onopen = function (ws_url) {
    	var el=$.tips({
            content:'聊天室连接成功！',
            stayTime:2000,
            type:"success"
        });
        el.on("tips:hide",function(){
        });
    };
    _this.ws.onmessage = function (evt) {
        //analysisMessage(evt.data);  //解析后台传回的消息,并予以展示
    	message = JSON.parse(evt.data);
        if(message.records != null && message.records != undefined){      //历史留言
        	for(var i=0;i<message.records.length;i++){
        		//analysisMessage(message.records[i]);
        		var m = JSON.parse(message.records[i]);
        		_this.event[m.type](m);
        	}
        }
        _this.event[message.type](message);
        if(message.type == "logout"){
        	_this.close();
        }
        if(message.members != null && message.members != undefined){      //在线列表
            _this.event["online"](message.members);
        }
    	
    };
    _this.ws.onerror = function (evt) {
    	discovery.splice(inde,1); //删除异常节点
    	_this.connection(room,discovery); //递归轮询
    	/*var el=$.tips({
            content:'连接异常!',
            stayTime:2000,
            type:"warn"
        });
        el.on("tips:hide",function(){
            console.log("tips hide");
        });*/
    };
    _this.ws.onclose = function (evt) {
    	_this.connection(room,discovery); //递归轮询
    	var el=$.tips({
            content:'已经关闭连接!',
            stayTime:2000,
            type:"warn"
        });
        el.on("tips:hide",function(){
        });
    };
};

WebChat.prototype.close = function(){
	var msg = "未开启连接";
	if(this.ws != null){
		this.ws.close();
		this.ws = null;
        msg = "已经关闭连接";
	}
	var el=$.tips({
        content: msg,
        stayTime: 2000,
        type: "warn"
    });
    el.on("tips:hide",function(){
    });
}

WebChat.prototype.check = function(){
	if(this.ws != null){
		return this.ws.readyState != 0;
	}
	return false;
}

WebChat.prototype.send = function(to,message){
	if(!this.check()){
		return false;
	}
	this.ws.send(JSON.stringify({
		type : "text",
        content : message,
        to : to      //接收人,如果没有则置空,如果有多个接收人则用,分隔
    }));
	return true;
};

WebChat.prototype.sendImages = function(to,uri){
	if(!this.check()){
		return false;
	}
	this.ws.send(JSON.stringify({
		type : "images",
        content : uri,
        to : to      //接收人,如果没有则置空,如果有多个接收人则用,分隔
    }));
	return true;
};