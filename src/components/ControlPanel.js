import React from "react";

const ControlPanel = ({ onStart, onStop, onReset }) => {
  return (
    <div>
      <h2>Control Panel</h2>
      <button onClick={onStart}>Start</button>
      <button onClick={onStop}>Stop</button>
      <button onClick={onReset}>Reset</button>
    </div>
  );
};

export default ControlPanel;
