package org.example;

import java.util.Scanner;
public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String jsonFilePath = "configuration.json";
        Configuration configuration = new Configuration();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Load configuration from JSON and start simulation");
            System.out.println("2. Enter configuration manually");
            System.out.println("3. Show configuration data");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    // Load configuration from JSON file
                    if (configuration.loadFromFile(jsonFilePath)) {
                        TicketPool ticketPool = new TicketPool(configuration.getMaximumTicketCapacity());
                        simulateVendorsAndCustomers(configuration, ticketPool);
                    } else {
                        System.out.println("Failed to load configuration. Please enter configuration manually.");
                    }
                    break;
                case 2:
                    configuration.setConfigurations(jsonFilePath);
                    break;
                case 3:
                    configuration.showConfiguration();
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Simulate vendors and customers using the provided configuration
    private static void simulateVendorsAndCustomers(Configuration configuration, TicketPool ticketPool) {
        int totalTickets = configuration.getTotalNumberOfTickets();
        int ticketReleaseRate = configuration.getTicketReleaseRate();
        int customerRetrievalRate = configuration.getCustomerRetrievalRate();

        // Create and start 2 vendor threads
        Thread[] vendorThreads = new Thread[2];
        for (int i = 0; i < 2; i++) {
            vendorThreads[i] = new Thread(new Vendor(ticketPool, totalTickets / 2, ticketReleaseRate));
            vendorThreads[i].setName("Vendor-" + (i + 1));
            vendorThreads[i].start();
        }

        // Create and start 5 customer threads
        Thread[] customerThreads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            customerThreads[i] = new Thread(new Customer(ticketPool, customerRetrievalRate));
            customerThreads[i].setName("Customer-" + (i + 1));
            customerThreads[i].start();
        }

        // Wait for vendors to finish
        for (Thread vendorThread : vendorThreads) {
            try {
                vendorThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Signal customers to stop and wait for them to finish
        ticketPool.setSimulationComplete();
        for (Thread customerThread : customerThreads) {
            try {
                customerThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Simulation complete. All tickets have been sold.");
    }
}
