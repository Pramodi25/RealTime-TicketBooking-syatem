package com.example.TicketBooking.Backend.TicketBooking.config;

import com.example.TicketBooking.Backend.TicketBooking.Component.DashboardHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final DashboardHandler dashboardHandler;

    public WebSocketConfig(DashboardHandler dashboardHandler) {
        this.dashboardHandler = dashboardHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(dashboardHandler, "/websocket").setAllowedOrigins("*");
    }
}
