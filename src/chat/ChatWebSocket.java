package chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;


@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class ChatWebSocket {
    private Session session;
    private ChatService service;

    public ChatWebSocket(ChatService service) {
        this.service = service;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        service.add(this);
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {

        System.out.println(data);
        service.sendMessage(data);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        service.remove(this);
    }

    public void sendString(String data) {
        try {
            session.getRemote().sendString(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
