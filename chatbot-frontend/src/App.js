import React, { useState, useRef } from 'react';
import './App.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faClipboard, faFileExcel } from '@fortawesome/free-solid-svg-icons';


function App() {
  const [messages, setMessages] = useState([]);
  const [inputText, setInputText] = useState('');
  const chatDisplayRef = useRef(null);

  const handleInputChange = (event) => {
    setInputText(event.target.value);
  };

  const handleSendMessage = async () => {
    if (inputText.trim()) {
      const userMessage = { text: inputText, sender: 'user' };
      setMessages((prevMessages) => [...prevMessages, userMessage]);
      setInputText('');

      try {
        const response = await fetch('http://localhost:8080/api/chatbot', {
          method: 'POST',
          headers: {
            'Content-Type': 'text/plain',
          },
          body: inputText,
        });

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        let botResponseContent;
        let rawResults = null;

        if (data.results && Array.isArray(data.results) && data.results.length > 0) {
          rawResults = data.results;
          const headers = Object.keys(data.results[0]);
          botResponseContent = (
            <div>
              <table>
                <thead>
                  <tr>{headers.map(header => <th key={header}>{header}</th>)}</tr>
                </thead>
                <tbody>
                  {data.results.map((row, index) => (
                    <tr key={index}>
                      {headers.map(header => <td key={`${index}-${header}`}>{row[header]}</td>)}
                    </tr>
                  ))}
                </tbody>
              </table>
              <div className="data-actions">
                <button title="Copy to Clipboard" onClick={() => handleCopyToClipboard(rawResults)}>
                  <FontAwesomeIcon icon={faClipboard} />
                </button>
                <button title="Download as CSV" onClick={() => handleDownloadCSV(rawResults)}>
                  <FontAwesomeIcon icon={faFileExcel} />
                </button>
              </div>
            </div>
          );
        } else if (data.error) {
          botResponseContent = <div className="error-message">Error: {data.error}</div>;
        } else if (data.response) {
          botResponseContent = <div className="bot-text">{data.response}</div>;
        } else {
          botResponseContent = <div className="bot-text">No response from the bot.</div>;
        }

        const botMessage = { content: botResponseContent, sender: 'bot' };
        setMessages((prevMessages) => [...prevMessages, botMessage]);

        if (chatDisplayRef.current) {
          chatDisplayRef.current.scrollTop = chatDisplayRef.current.scrollHeight;
        }

      } catch (error) {
        console.error('Failed to send message to backend:', error);
        const errorMessage = { text: 'Failed to get response from the bot.', sender: 'bot' };
        setMessages((prevMessages) => [...prevMessages, errorMessage]);
      }
    }
  };

  const handleCopyToClipboard = (results) => {
    if (results && results.length > 0) {
      const headers = Object.keys(results[0]);
      const csvRows = [headers.join(',')];
      results.forEach(row => csvRows.push(headers.map(header => row[header]).join(',')));
      navigator.clipboard.writeText(csvRows.join('\n'));
      alert('Data copied to clipboard!');
    } else {
      alert('No data to copy.');
    }
  };

  const handleDownloadCSV = (results) => {
    if (results && results.length > 0) {
      const headers = Object.keys(results[0]);
      const csvRows = [headers.join(',')];
      results.forEach(row => csvRows.push(headers.map(header => row[header]).join(',')));
      const csvString = csvRows.join('\n');
      const blob = new Blob([csvString], { type: 'text/csv' });
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'chatbot_data.csv';
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
      window.URL.revokeObjectURL(url);
    } else {
      alert('No data to download.');
    }
  };

  return (
    <div className="chat-container">
      <div className="chat-display" ref={chatDisplayRef}>
        {messages.map((message, index) => (
          <div key={index} className={`message ${message.sender}`}>
            {message.sender === 'user' ? message.text : message.content}
          </div>
        ))}
      </div>
      <div className="chat-input-area"> {/* New container for input at the bottom */}
        <input
          type="text"
          value={inputText}
          onChange={handleInputChange}
          placeholder="Ask me anything..."
        />
        <button onClick={handleSendMessage}>Send</button>
      </div>
    </div>
  );
}

export default App;