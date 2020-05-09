package by.brausov.endpoints;

import by.brausov.coders.MessageDecoder;
import by.brausov.coders.MessageEncoder;
import by.brausov.model.entities.Message;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@ServerEndpoint(value = "/chat/{user}", decoders = {MessageDecoder.class}, encoders = {MessageEncoder.class})
public class ChatEndpoint {

    private Session session = null;
    private int id = 0;
    private String username = "guest";
    private static List<Session> sessionList = new LinkedList<Session>();

    @OnOpen
    public void onOpen(Session session, @PathParam("user") String username) {
        this.session = session;
        this.username = username.substring(0, username.indexOf("_"));
        this.id = Integer.parseInt(username.substring(username.indexOf("_") + 1));
        sessionList.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessionList.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, final Message msg) {
        msg.setId(this.id);
        msg.setName(this.username);
        sessionList.forEach(s->{
            if(s == this.session) return;
            try {
                s.getBasicRemote().sendObject(msg);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (EncodeException e) {
                e.printStackTrace();
            }
        });
    }


}

