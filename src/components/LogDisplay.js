import React from "react";

const LogDisplay = ({ logs }) => {
  return (
    <div>
      <h2>System Logs</h2>
      <div id="logDisplay">
        {logs.map((log, index) => (
          <p key={index}>{log}</p>
        ))}
      </div>
    </div>
  );
};

export default LogDisplay;
