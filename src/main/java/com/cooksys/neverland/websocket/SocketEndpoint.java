package com.cooksys.neverland.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.cooksys.neverland.jms.BasicJMS;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@ServerEndpoint("/notifications")
public class SocketEndpoint {

	private static Set<Session> clients = new HashSet<>();
	private static Gson gson = new Gson();
	public static BasicJMS jms = new BasicJMS();
	
	@OnOpen
	public void onOpen(Session session1) {
		System.out.println("user connected");
		clients.add(session1);
	}

	@OnMessage
	public static void recieveMessage(Session session1, String message) throws JsonSyntaxException, IOException {
		System.out.println(message);
		
		Message recieved = gson.fromJson(message, Message.class);
		
		Iterator<Session> iterator = clients.iterator();
		while (iterator.hasNext()) {
			Session client = iterator.next();
			if (client.isOpen()) {
				String jsObj = gson.toJson(recieved);
				client.getBasicRemote().sendText(jsObj);
			} else
				clients.remove(client);
		}
	}

	@OnClose
	public void onClose(CloseReason reason, Session session1) {
		clients.remove(session1);
	}
	
	public static void sendMsg(Message message) throws JsonSyntaxException, IOException  {
		Iterator<Session> iterator = clients.iterator();
		while (iterator.hasNext()) {
			Session client = iterator.next();
			if (client.isOpen()) {
				String jsObj = gson.toJson(message);

				client.getBasicRemote().sendText(jsObj);	
		
	}
	}
}}