package com.example.TicketBooking.Backend.TicketBooking.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "configurations")
public class Configuration {

    @JsonProperty("id")
    private String id;

    @JsonProperty("totalTickets")
    private int totalTickets;

    @JsonProperty("ticketReleaseRate")
    private int ticketReleaseRate;

    @JsonProperty("customerRetrievalRate")
    private int customerRetrievalRate;

    @JsonProperty("maxTicketCapacity")
    private int maxTicketCapacity;

    // Getters and Setters (if not using Lombok @Data)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }
}
