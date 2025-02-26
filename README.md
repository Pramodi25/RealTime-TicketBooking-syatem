The Real-Time Ticketing System is an all-in-one solution for handling ticket distribution and 
tracking in real-time. It comes packed with features that make managing tickets easier than ever.
• A front-end dashboard made with React to show data and let users interact with it..
• A backend implemented using Spring Boot for managing ticket-related operations and 
data.
Setup Instructions
Technology Stack
Backend
• Spring Boot
• MongoDB
• WebSocket
• Java
Frontend
• React
• Axios for API calls.
• CSS for styling.
• React Toastify for notifications.
Prerequisites
1. Backend Requirements
o Java Development Kit (JDK) 11 or higher.
o Apache Maven or Gradle for building the project.
o Spring Boot installed and configured.
2. Frontend Requirements
o Node.js version 16 or higher.
o npm (Node Package Manager) or yarn for dependency management.
Backend Setup
1. Navigate to the backend folder containing the BackendApplication.java.
2. Ensure all required dependencies are specified in your pom.xml file.
3. Build the project:
cmd command - mvn clean install
4. Start the backend server:
cmd command - java -jar target/BackendApplication.jar
The backend will start on http://localhost:8080 by default.
Frontend Setup
1. Navigate to the frontend folder containing the App.js and other related files.
2. Install dependencies:
cmd command - npm install
3. Start the React application:
cmd command - npm start
This will start the application on http://localhost:3000
Usage Instructions
Starting the System
1. Open the frontend in your browser at http://localhost:3000.
2. Use the Configuration Form to set up system parameters.
3. Click the Start button in the Control Panel to initialize the ticket system simulation.
System Controls
• Start: Begins the simulation and starts fetching tickets.
• Stop: Halts the simulation without clearing data.
• Reset: Stops the system and clears all tickets and logs.
Monitoring Tickets
• Ticket Status:
o Displays the number of active, completed, and failed tickets.
o Automatically updates in real-time.
• Logs:
o Shows system activities like configuration updates and ticket transactions.
1. The backend exposes REST APIs for managing tickets, customers, and vendors.
2. The frontend interacts with these APIs to fetch and display data.
3. Ensure both frontend and backend servers are running for the system to function 
properly
