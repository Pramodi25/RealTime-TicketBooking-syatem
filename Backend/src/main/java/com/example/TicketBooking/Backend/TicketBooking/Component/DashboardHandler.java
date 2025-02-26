package com.example.TicketBooking.Backend.TicketBooking.Component;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.stereotype.Component;

@Component
public class DashboardHandler extends TextWebSocketHandler {
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Received: " + payload);

        // Echo message back to client
        session.sendMessage(new TextMessage("Server received: " + payload));
    }
}
