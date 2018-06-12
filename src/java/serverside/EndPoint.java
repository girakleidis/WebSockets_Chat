/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverside;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author george
 */
@ServerEndpoint("/hello")
public class EndPoint {

    @Inject
    private SessionRegistry sessionRegistry;

    @OnOpen
    public void open(Session session, EndpointConfig conf) {
        sessionRegistry.add(session);
    }

    @OnClose
    public void close(Session session) {
        sessionRegistry.remove(session);
    }

    @OnMessage
    public void waitingmessage(String message, Session sender) throws IOException {
        List<Session> ls = sessionRegistry.getAll();

        for (Session s : ls) {
            s.getBasicRemote().sendText("User id: " + sender.getId() + " said: " + message);
        }
    }

}
