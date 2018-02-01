function KTime(param) {
	this.timer = null, this.param = {
		time : 0,
		speed : 1000,
		onPerSecondCall : function() {
		},
		onTimeOverCall : function() {
		}
	}

	this.param = $.extend(this.param, param);
	this.timer = null;

	var _this = this;

	this.restart = function() {
		_this.pause();
		_this.start();
	};

	this.start = function() {
		_this.run();
		_this.timer = setInterval(function() {
			_this.run();
		}, _this.param.speed);
	};

	this.run = function() {
		if (_this.param.time > 0) {
			_this.param.onPerSecondCall(_this.format(_this.param.time));
		} else {
			_this.stop();
			_this.param.onTimeOverCall();
		}
		_this.param.time--;
	};

	this.pause = function() {
		if (_this.timer)
			clearInterval(_this.timer);
	};

	this.stop = function() {
		if (_this.timer) {
			_this.setTime(0);
			clearInterval(_this.timer);
		}
	};

	this.setTime = function(time) {
		_this.param.time = time;
	};

	this.format = function(a) {
		var b = {
			hour : 0,
			minute : 0,
			second : 0
		};

		b.hour = parseInt(a / 3600);
		b.minute = parseInt((a % 3600) / 60);
		b.second = a % 60;
		if (parseInt(b.hour) < 10) {
			b.hour = "0" + b.hour
		}
		if (parseInt(b.minute) < 10) {
			b.minute = "0" + b.minute
		}
		if (parseInt(b.second) < 10) {
			b.second = "0" + b.second
		}
		return b
	};
}