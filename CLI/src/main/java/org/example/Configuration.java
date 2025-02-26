package org.example;

import static org.example.Main.scanner;

public class Configuration implements FileHandler {
    private int totalNumberOfTickets;
    private int customerRetrievalRate;
    private int ticketReleaseRate;
    private int maximumTicketCapacity;

    public Configuration() {
    }

    public void showConfiguration() {
        System.out.println("\nConfiguration Details:");
        System.out.println("Total Number of Tickets: " + totalNumberOfTickets);
        System.out.println("Customer Retrieval Rate: " + customerRetrievalRate);
        System.out.println("Ticket Release Rate: " + ticketReleaseRate);
        System.out.println("Maximum Ticket Capacity: " + maximumTicketCapacity);
    }

    public int getTotalNumberOfTickets() {
        return totalNumberOfTickets;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getMaximumTicketCapacity() {
        return maximumTicketCapacity;
    }

    // Save the Configuration object as JSON and Text files
    public void saveToFile(String filePath) {
        Utils.saveToFile(this, filePath);
    }

    // Load the Configuration object from a JSON file
    public boolean loadFromFile(String jsonFilePath) {
        Configuration loadedConfig = Utils.loadFromFile(jsonFilePath, Configuration.class);
        if (loadedConfig != null) {
            this.totalNumberOfTickets = loadedConfig.getTotalNumberOfTickets();
            this.customerRetrievalRate = loadedConfig.getCustomerRetrievalRate();
            this.ticketReleaseRate = loadedConfig.getTicketReleaseRate();
            this.maximumTicketCapacity = loadedConfig.getMaximumTicketCapacity();
            return true;
        }
        return false;
    }


    public void setConfigurations(String filePath) {
        this.totalNumberOfTickets = getPositiveInput("Enter the total number of tickets (positive number): ");
        this.ticketReleaseRate = getPositiveInput("Enter the ticket release rate (per second, positive number): ");
        this.customerRetrievalRate = getPositiveInput("Enter the customer retrieval rate (per second, positive number): ");
        this.maximumTicketCapacity = getPositiveInput( "Enter the maximum ticket capacity (positive number): ");

        this.saveToFile(filePath);
        System.out.println("Configuration saved successfully.");
    }

    private int getPositiveInput(String prompt) {
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next(); // Clear invalid input
            }
            input = scanner.nextInt();
            if (input <= 0) {
                System.out.println("Input must be a positive number.");
            }
        } while (input <= 0);
        return input;
    }
}