package com.message.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@Controller
@ServerEndpoint("/messageSocket/{userId}")
public class SocketServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketServer.class);

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    private static Map<String,String> sessionSocket = Collections.synchronizedMap(new HashMap<>());

    public static Set<Session> getAllSession() {
        return peers;
    }

    public static Map<String, String> getSessionSocket() {
        return sessionSocket;
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userId") String userId) {

        LOGGER.info("received message from session id : " + session.getId());

        try
        {
            session.getBasicRemote().sendText("Test Message");
            LOGGER.info("Send message to peer session: "+ session.getId() + " and message is : " + message);
        }catch (IOException e) {
            LOGGER.error("Error On Message :" + e.getMessage());
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        LOGGER.info("Opened websocket channel for session : " + session.getId());
        LOGGER.info("Opened websocket channel for http session : " + userId);
        sessionSocket.put(session.getId(),userId);
        peers.add(session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        LOGGER.info("Closed websocket channel for session : " + session.getId());
        peers.remove(session);
        sessionSocket.remove(session.getId());
    }

    @OnError
    public void onError(Throwable error)
    {
        LOGGER.error("Error Occured In WebSocket : " + error.getMessage());
    }
}