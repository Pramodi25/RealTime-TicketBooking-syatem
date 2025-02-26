import React from "react";
import './TicketStatus.css';

const TicketStatus = () => {
  return (
    <div className="ticket-status-container">
      <h2 className="ticket-status-header">Ticket Pool Status</h2>
      <div className="status-box">
        <p>System Status:</p>
        <h3 className="stopped">Stopped</h3>
      </div>
      <div className="status-box">
        <p>Active Tickets:</p>
        <h3>0</h3>
      </div>
      <div className="status-box">
        <p>Completed Tickets:</p>
        <h3>0</h3>
      </div>
      <div className="status-box">
        <p>Failed Tickets:</p>
        <h3>0</h3>
      </div>
    </div>
  );
};

export default TicketStatus;
