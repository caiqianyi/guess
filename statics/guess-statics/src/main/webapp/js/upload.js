(function () {
    var input = document.querySelector('input');

    input.onchange = function () {
        lrz(this.files[0], {width: 400}, function (results) {
            // 你需要的数据都在这里，可以以字符串的形式传送base64给服务端转存为图片。
            console.log(results);
            // 以下为演示用内容
            var tip = document.querySelector('#tip'),
                report = document.querySelector('#report'),
                footer = document.querySelector('footer');

            tip.innerHTML = '<p>正在生成和上传..</p> <small class="text-muted">演示使用了大量内存，可能会造成几秒内卡顿，不代表真实表现，请亲测。</small>';
            demo_report('原始图片', results.blob, results.origin.size);
            setTimeout(function () {

                demo_report('客户端预压的图片', results.base64, results.base64.length * 0.8);
                console.info(results);
                // 发送到后端
                var xhr = new XMLHttpRequest();
                var data = {
                    base64: results.base64,
                    size: results.base64.length // 校验用，防止未完整接收
                };

               /* xhr.open('POST', '/');
                xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var result = JSON.parse(xhr.response);

                        result.error
                            ? alert('服务端错误，未能保存图片')
                            : demo_report('服务端实存的图片', result.src, result.size);

                        tip.innerHTML = '<p>生成和上传完毕</p> <small class="text-muted">演示使用了大量内存，可能会造成几秒内卡顿，不代表真实表现，请亲测。</small>';
                    }
                };

                xhr.send(JSON.stringify(data)); // 发送base64
*/          }, 100);
        });
    };

    /**
     * 演示报告
     * @param title
     * @param src
     * @param size
     */
    function demo_report(title, src, size) {
        var img = new Image(),
            li = document.createElement('li'),
            size = (size / 1024).toFixed(2) + 'KB';

        img.onload = function () {
            var content = '<ul>' +
                '<li>' + title + '（' + img.width + ' X ' + img.height + '）</li>' +
                '<li class="text-cyan">' + size + '</li>' +
                '</ul>';

            li.className = 'item';
            li.innerHTML = content;
            li.appendChild(img);
            document.querySelector('#report').appendChild(li);
        };

        img.src = src;
    }
    
    var recorder;  
    var audio = document.querySelector('audio');  
      
    function startRecording() {
		HZRecorder.get(function (errcode,rec) {
			if(errcode == 0){
				recorder = rec;  
				recorder.start();
				return;
			}
			$.tips({
    	        content: rec,
    	        stayTime: 2000,
    	        type: "warn"
    	    });
		});  
    }  
      
      
    function obtainRecord(){  
        var record = recorder.getBlob();  
        debugger;  
    };  
      
    function stopRecord(){  
        recorder.stop();  
    };  
      
    function playRecord(){  
        recorder.play(audio);  
    };  
      
    /* 视频 */  
    function scamera() {  
        var videoElement = document.getElementById('video1');  
        var canvasObj = document.getElementById('canvas1');  
        var context1 = canvasObj.getContext('2d');  
        context1.fillStyle = "#ffffff";  
        context1.fillRect(0, 0, 320, 240);  
        context1.drawImage(videoElement, 0, 0, 320, 240);  
    };  
      
    function playVideo(){  
        var videoElement1 = document.getElementById('video1');  
        var videoElement2 = document.getElementById('video2');  
        videoElement2.setAttribute("src", videoElement1);  
    };
    
    $("#btn-startVoice").click(function(){
    	startRecording();
    });
    $("#btn-showVoice").click(function(){
    	obtainRecord();
    });
    $("#btn-stopVoice").click(function(){
    	stopRecord();
    });
    $("#btn-playVoice").click(function(){
    	playRecord();
    });
})();