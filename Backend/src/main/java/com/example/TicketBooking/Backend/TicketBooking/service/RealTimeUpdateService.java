//package com.example.TicketBooking.Backend.TicketBooking.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RealTimeUpdateService {
//
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//    public void sendUpdate(String topic, Object payload) {
//        messagingTemplate.convertAndSend(topic, payload);
//    }
//}
