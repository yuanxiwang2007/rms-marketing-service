<!DOCTYPE HTML>
<html>
<head>
    <title>My WebSocket</title>
    <script src="${request.contextPath}/static/js/jquery-1.11.1.min.js"></script>
    <script src="${request.contextPath}/static/js/qrcode.js"></script>
</head>

<body>
Welcome<br/>

步骤一：加载二维码
<img id="qrImg" src="http://urine-analysis-dev.doctorwork.com/urine/v1/qr/qrshow?macID=xxxx"></br>

<input id="macID" type="text" /><button onclick="qrShow()">输入机器码</button></br>
<div>步骤二：扫码登录</div>
<input id="openID" type="text" /><button onclick="loginByQrcode()">输入openID扫码登陆</button>
<div id="message">
</div>
步骤三用户操作
<input id="httpText" type="text" /><button onclick="post()">post</button>
<div id="httpReturnMsg" style="backcolor: red;height: 100px;width:100px;"/></br>
以下为测试按钮
<input id="test" type="text" /><button onclick="sendMsgByWS()">sendMsgByWS</button>

</body>

<script type="text/javascript">
    var url="urinesetting-analysis-dev.doctorwork.com";
//    var url="localhost:9090";
    var websocket = null;
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function(){
        websocket.close();
    }
    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML){
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    //生成二维码
    function qrShow(){
        var macID = document.getElementById('macID').value;
        document.getElementById('qrImg').setAttribute("src","http://"+url+"/urinesetting/v1/qr/qrshow?macID="+macID);
        setInterval(qrcheck(), 5000);
    }
    //验证二维码
    function qrcheck(){
        var macID = document.getElementById('macID').value;
        $.ajax({
            type: "GET",
            url: "/urinesetting/v1/qr/qrcheck",
            dataType: "json",
            data: {"macID":macID},
            success: function(response){
                document.getElementById('message').innerHTML += response + '<br/>';
            }
        });
    }

    //扫码登陆
    function loginByQrcode(){
        var openID = document.getElementById('openID').value;
        var macID = document.getElementById('macID').value;
        $.ajax({
            type: "GET",
            url: "/urinesetting/v1/qr/loginByQrcode",
            dataType: "json",
            data: {"macID":macID,"openID":openID},
            success: function(response){
                //判断当前浏览器是否支持WebSocket
                if('WebSocket' in window){
                    websocket = new WebSocket("ws://"+url+"/v1/websocket");
                }
                else{
                    alert('Not support websocket')
                }
                //连接发生错误的回调方法
                websocket.onerror = function(){
                    setMessageInnerHTML("error");
                };

                //连接成功建立的回调方法
                websocket.onopen = function(event){
                    setMessageInnerHTML("open");
                }

                //接收到消息的回调方法
                websocket.onmessage = function(event){
                    setMessageInnerHTML(event.data);
                }

                //连接关闭的回调方法
                websocket.onclose = function(){
                    setMessageInnerHTML("close");
                }
//                setInterval("alert('11')", 5000);
//                websocket.send("{key:'0',openID:'"+openID+"'}");'
                setInterval("websocket.send('{\"key\":0,\"openID\":\""+openID+"\"}')", 5000);
            }
        });
    }

    function post(){
        var message = document.getElementById('httpText').value;
        var openID = document.getElementById('openID').value;
        $.ajax({
            type: "POST",
            url: "/urinesetting/v1/mobile/sendMsg",
            dataType: "json",
            data: {"msg":message,"openID":openID},
            success: function(response){
                document.getElementById('httpReturnMsg').innerHTML += response.name + '<br/>';
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            },

        });
    }
    function sendMsgByWS(){
        var openID = document.getElementById('openID').value;
        websocket.send("{'key':'0','openID':'"+openID+"'}");
    }
</script>
</html>