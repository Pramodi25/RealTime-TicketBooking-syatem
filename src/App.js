import React, { useState } from "react";
import axios from "axios";
import "./App.css";

const App = () => {
  const [status, setStatus] = useState("");
  const [logs, setLogs] = useState([]);
  const [config, setConfig] = useState({
    totalNumberOfTickets: "",
    customerRetrievalRate: "",
    ticketReleaseRate: "",
    maximumTicketCapacity: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setConfig((prevConfig) => ({
      ...prevConfig,
      [name]: value,
    }));
  };

  const submitConfiguration = async () => {
    try {
      await axios.post("http://localhost:8080/api/tickets/config", {
        totalTickets: parseInt(config.totalNumberOfTickets, 10),
        customerRetrievalRate: parseInt(config.customerRetrievalRate, 10),
        ticketReleaseRate: parseInt(config.ticketReleaseRate, 10),
        maxTicketCapacity: parseInt(config.maximumTicketCapacity, 10),
      });
      setStatus("Configuration updated successfully!");
      setLogs((prevLogs) => [...prevLogs, "Configuration updated."]);
    } catch (error) {
      console.error("Error submitting configuration:", error);
      setStatus("Failed to update configuration.");
    }
  };

  const startSystem = async () => {
    try {
      await axios.post("http://localhost:8080/api/tickets/system/start");
      setStatus("System started successfully!");
      setLogs((prevLogs) => [...prevLogs, "System started."]);
    } catch (error) {
      console.error("Error starting the system:", error);
      setStatus("Failed to start the system.");
    }
  };

  const stopSystem = async () => {
    try {
      await axios.post("http://localhost:8080/api/tickets/system/stop");
      setStatus("System stopped successfully!");
      setLogs((prevLogs) => [...prevLogs, "System stopped."]);
    } catch (error) {
      console.error("Error stopping the system:", error);
      setStatus("Failed to stop the system.");
    }
  };

  const resetSystem = async () => {
    try {
      await axios.post("http://localhost:8080/api/tickets/system/reset");
      setStatus("System reset successfully!");
      setLogs((prevLogs) => [...prevLogs, "System reset."]);
    } catch (error) {
      console.error("Error resetting the system:", error);
      setStatus("Failed to reset the system.");
    }
  };

  return (
    <div className="container">
      {/* Left Panel */}
      <div className="left-panel">
        <h2>Enter Configuration</h2>
        <div className="config-form">
          <div>
            <label>Total Number of Tickets:</label>
            <input
              type="number"
              name="totalNumberOfTickets"
              value={config.totalNumberOfTickets}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label>Customer Retrieval Rate:</label>
            <input
              type="number"
              name="customerRetrievalRate"
              value={config.customerRetrievalRate}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label>Ticket Release Rate:</label>
            <input
              type="number"
              name="ticketReleaseRate"
              value={config.ticketReleaseRate}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label>Maximum Ticket Capacity:</label>
            <input
              type="number"
              name="maximumTicketCapacity"
              value={config.maximumTicketCapacity}
              onChange={handleInputChange}
            />
          </div>
          <button onClick={submitConfiguration}>Submit Configuration</button>
        </div>

        {/* Start/Stop/Reset Buttons */}
        <div className="control-buttons">
          <button onClick={startSystem}>Start</button>
          <button onClick={stopSystem}>Stop</button>
          <button onClick={resetSystem}>Reset</button>
        </div>
      </div>

      {/* Right Panel */}
      <div className="right-panel">
        <div className="panel-section">
          <h2>Status</h2>
          <p>{status}</p>
        </div>
        <div className="panel-section">
          <h2>Logs</h2>
          <ul>
            {logs.map((log, index) => (
              <li key={index}>{log}</li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default App;
