package com.goldcow.emanage.util.gen.bean;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.goldcow.sframe.util.GlobalVal;

public class SystemWebSocketHandler implements WebSocketHandler {

	private static final Logger logger = Logger.getLogger(SystemWebSocketHandler.class);
	 
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1)
			throws Exception {
		logger.debug("websocket已关闭连接！");
        users.remove(session);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
        logger.debug("websocket连接成功!");
		users.add(session);
		/*String userName = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
		
		System.out.println("userName:"+userName);
		
		if(userName !=null){
			int count = 5;
            session.sendMessage(new TextMessage(count + ""));
		}*/
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
			throws Exception {
		sendMessageToUsers(new TextMessage(message.getPayload() + ""));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable arg1)
			throws Exception {
		if(session.isOpen()){
            session.close();
        }
        logger.debug("websocket连接出错，关闭连接!");
        users.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
	
	/**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("WEBSOCKET_USERNAME").equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
