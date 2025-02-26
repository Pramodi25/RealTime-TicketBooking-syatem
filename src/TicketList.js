import React, { useEffect, useState } from 'react';
import axios from 'axios';

const TicketList = () => {
  const [tickets, setTickets] = useState([]);

  useEffect(() => {
    axios.get('/tickets')
      .then(response => {
        setTickets(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the tickets!', error);
      });
  }, []);

  return (
    <div>
      <h1>Tickets</h1>
      <ul>
        {tickets.map((ticket, index) => (
          <li key={index}>{ticket.name}</li>
        ))}
      </ul>
    </div>
  );
};

export default TicketList;
