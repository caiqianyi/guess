function IssueTimer(param) {
	var args = {
		kindOf : "bjpk10",
		/**
		 * 当获取到新期号时，执行此回调执行次函数
		 */
		onPullNewIssue : function(issue){},
		/**
		 * 当前期号倒计时，每秒回调执行次函数
		 */
		onCounterPerSecond : function(date){},
		/**
		 * 当前期号结束时，回调执行次函数
		 */
		onCounterOver : function(issue){},
		
		/**
		 * 开奖倒计时开始之前，回调执行次函数
		 */
		onRefCounterBefore: function(expect){},
		/**
		 * 开奖倒计时，每秒回调执行次函数
		 */
		onRefCounterPerSecond : function(date){},
		/**
		 * 开奖倒计时结束时，回调执行次函数
		 */
		onRefCounterOver : function(){},
		/**
		 * 当刷新到开奖号时，执行此函数
		 */
		onRefreshData : function(datas,flag){}
	};
	
	this.param = $.extend(args,param);
	this.current = null;
	this.counter = null;
	this.refcounter = null;
	
	var _this = this;
	this.open = function() {
		oauth2.ajax({
	    	url: "/lottery/issue/"+_this.param.kindOf,
	    	type: "GET",
	    	success: function(response){
	    		var data = response.data;
	    		
	    		if(!_this.current || _this.current.expect != data.expect){
	    			var f = {
	    				pass : (data.endTime - data.startTime) / 1000 - data.timeRemaining, 
	    				timeRemaining: data.timeRemaining,
	    				
	    			};
	    			if(!_this.current) f.expect = data.expect - 1;
	    			else f.expect = _this.current.expect;
	    			
	    			_this.current = data;
	    			
	    			_this.refreshData(f);
	    			_this.param.onPullNewIssue(_this.current);
	    			
	    			if(!_this.counter)
	    				_this.counter = new KTime({
			    			time:0,
			    			onPerSecondCall:function(date){
			    				_this.param.onCounterPerSecond(date);
			    			},
			    			onTimeOverCall:function(){
			    				_this.param.onCounterOver(_this.current.expect);
			    				_this.open();
			    			}
			    		});
	    			_this.counter.setTime(data.timeRemaining);
	    			_this.counter.restart();
	    			return;
	    		}
	    		
	    		//如果没有获取到期号，一秒后递归获取
	    		setTimeout(function (){
	    			_this.open();
	    		},1000);
	    	}
	    });
	};
	
	this.lastTime = null;
	this.isRefresh = function(data){
		return !_this.lastTime || _this.lastTime != data.last_time;
	};
	
	this.refreshData = function(flag){
		oauth2.ajax({
			url: "/lottery/yllr/"+_this.param.kindOf+"/200",
			type: "GET",
			success: function(response){
				var data = response.data;
				var isRefresh = _this.isRefresh(data);
				
				if(isRefresh || flag){//数据是最新的
					_this.lastTime = data.last_time;
					_this.param.onRefreshData(data,flag);
					if(!flag) return;
				}
				if(flag){//开启任务
					var time = data.openSecond - flag.pass;
					if(time > 0){
						_this.param.onRefCounterBefore(flag.expect);
						if(!_this.refcounter)
							_this.refcounter = new KTime({
								time:0,
								onPerSecondCall:function(date){
									_this.param.onRefCounterPerSecond(date);
								},
								onTimeOverCall:function(){
									_this.param.onRefCounterOver();
									_this.refreshData();
								}
							});
						_this.refcounter.setTime(time);
						_this.refcounter.restart();
					}
	    			return;
				}
				
				//没有获取最新数据，每5秒获取一次。直到获取为止
				setTimeout(function (){
	    			_this.refreshData();
	    		},5000);
			}
		});
	};
}