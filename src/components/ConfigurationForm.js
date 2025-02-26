import React, { useState } from "react";

const ConfigurationForm = ({ onSubmit }) => {
  const [maxCapacity, setMaxCapacity] = useState(20);
  const [releaseRate, setReleaseRate] = useState(5);
  const [purchaseRate, setPurchaseRate] = useState(3);
  const [totalTickets, setTotalTickets] = useState(100);

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({ maxCapacity, releaseRate, purchaseRate, totalTickets });
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Configuration Settings</h2>
      <label>
        Total Number of Tickets:
        <input
          type="number"
          value={totalTickets}
          onChange={(e) => setTotalTickets(e.target.value)}
        />
      </label>
      <label>
        Ticket Release Rate (ms):
        <input
          type="number"
          value={releaseRate}
          onChange={(e) => setReleaseRate(e.target.value)}
        />
      </label>
      <label>
        Ticket Purchase Rate (ms):
        <input
          type="number"
          value={purchaseRate}
          onChange={(e) => setPurchaseRate(e.target.value)}
        />
      </label>
      <label>
        Max Ticket Capacity:
        <input
          type="number"
          value={maxCapacity}
          onChange={(e) => setMaxCapacity(e.target.value)}
        />
      </label>
      <button type="submit">Save Configuration</button>
    </form>
  );
};

export default ConfigurationForm;
