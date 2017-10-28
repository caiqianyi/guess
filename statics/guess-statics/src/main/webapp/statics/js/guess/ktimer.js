function KTimer(a, c, b) {
	this._init(a, c, b)
}
KTimer.prototype = {
	time : null,
	speed : 1000,
	minute : 0,
	second : 0,
	interval : null,
	callback : function(a) {
	},
	processCallback : function(a) {
	},
	_init : function(a, c, b) {
		this.time = a;
		this.endtime = Date.parse(new Date()) + (a * 1000);
		this.callback = c;
		this.processCallback = b
	},
	run : function() {
		var b = Date.parse(new Date());
		if (b >= this.endtime) {
			this.time = 0
		} else {
			this.time = (this.endtime - b) / 1000
		}
		if (this.interval) {
			if (this.time <= 0) {
				clearInterval(this.interval);
				this.callback();
				return
			}
			this.time = parseInt(this.time - (this.speed / 1000));
			this.minute = parseInt(this.time / 60);
			this.second = this.time % 60;
			this.second = this.second < 0 ? "0" : this.second;
			if (parseInt(this.minute) < 10) {
				this.minute = "0" + this.minute
			}
			if (parseInt(this.second) < 10) {
				this.second = "0" + this.second
			}
			if (parseInt(this.time) <= 0) {
				clearInterval(this.interval);
				this.callback();
				return
			}
			this.processCallback(this)
		} else {
			var a = this;
			this.interval = setInterval(function() {
				a.run()
			}, a.speed)
		}
	},
	format : function(a) {
		var b = {
			hour : 0,
			minute : 0,
			second : 0
		};
		b.hour = parseInt(a / 3600);
		b.minute = parseInt(a / 60);
		b.second = a % 60;
		b.second = b.second < 0 ? "0" : b.second;
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
	},
	setting : function(b) {
		this.time = b;
		this.endtime = Date.parse(new Date()) + (b * 1000);
		var a = this;
		this.interval = setInterval(function() {
			a.run()
		}, a.speed)
	},
	stop : function() {
		this.time = 0;
		this.callback = function(a) {
		}
	}
};
