/**
 * 消息连接建立
 * 
 * @author wubin
 * @version v1.0
 * @since 2016-1-18
 */

function webSocket(){
	 var websocket;
     if ('WebSocket' in window) {
         websocket = new WebSocket("ws://localhost:8080/HRERP/webSocketServer");
     } else if ('MozWebSocket' in window) {
         websocket = new MozWebSocket("ws://localhost:8080/HRERP/webSocketServer");
     } else {
         websocket = new SockJS("http://localhost:8080/HRERP/sockjs/webSocketServer");
     }
     websocket.onopen = function (evnt) {
    	 //连接成功后请求数据
    	 util.get("/HRERP/message/request");
    	 
    	 
    	 alert("webSocket连接成功!");
     };
     websocket.onmessage = function (evnt) {
    	 alert("消息："+evnt.data);
         //$("#login_wrong").html("(<font color='red'>"+evnt.data+"</font>)")
     };
     websocket.onerror = function (evnt) {
     };
     websocket.onclose = function (evnt) {
     };
}





