import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link, useLocation } from 'react-router-dom';
import RoomsPage from './components/RoomsPage';
import BookingPage from './components/BookingPage';
import BookingsList from './components/BookingsList';

function App() {
  return (
    <Router>
      <AppContent />
    </Router>
  );
}

function AppContent() {
  const location = useLocation();

  const containerStyle = {
    display: 'flex',
    minHeight: '100vh',
    backgroundColor: '#f5f5f5',
    fontFamily: 'Arial, sans-serif'
  };

  const sidebarStyle = {
    width: '250px',
    backgroundColor: '#2c3e50',
    color: 'white',
    padding: '0',
    boxShadow: '2px 0 5px rgba(0,0,0,0.1)',
    position: 'fixed',
    height: '100vh',
    left: 0,
    top: 0
  };

  const headerStyle = {
    padding: '25px 20px',
    backgroundColor: '#34495e',
    borderBottom: '3px solid #3498db',
    textAlign: 'center'
  };

  const titleStyle = {
    margin: 0,
    fontSize: '20px',
    fontWeight: 'bold',
    color: 'white'
  };

  const subtitleStyle = {
    margin: '5px 0 0 0',
    fontSize: '12px',
    color: '#bdc3c7',
    fontWeight: 'normal'
  };

  const menuStyle = {
    listStyle: 'none',
    padding: '20px 0',
    margin: 0
  };

  const menuItemStyle = (isActive) => ({
    padding: '15px 25px',
    margin: '5px 0',
    cursor: 'pointer',
    transition: 'all 0.3s',
    backgroundColor: isActive ? '#3498db' : 'transparent',
    borderLeft: isActive ? '4px solid #2980b9' : '4px solid transparent'
  });

  const linkStyle = {
    color: 'white',
    textDecoration: 'none',
    fontSize: '15px',
    fontWeight: '500',
    display: 'block'
  };

  const contentStyle = {
    marginLeft: '250px',
    flex: 1,
    padding: '30px',
    backgroundColor: '#f5f5f5'
  };

  const footerStyle = {
    position: 'absolute',
    bottom: '20px',
    left: '0',
    right: '0',
    textAlign: 'center',
    color: '#95a5a6',
    fontSize: '12px',
    padding: '0 20px'
  };

  return (
    <div style={containerStyle}>
      <div style={sidebarStyle}>
        <div style={headerStyle}>
          <h1 style={titleStyle}>Room Booking</h1>
          <p style={subtitleStyle}>Management System</p>
        </div>
        
        <ul style={menuStyle}>
          <li style={menuItemStyle(location.pathname === '/')}>
            <Link to="/" style={linkStyle}>Manage Rooms</Link>
          </li>
          <li style={menuItemStyle(location.pathname === '/booking')}>
            <Link to="/booking" style={linkStyle}>Book Room</Link>
          </li>
          <li style={menuItemStyle(location.pathname === '/bookings')}>
            <Link to="/bookings" style={linkStyle}>My Bookings</Link>
          </li>
        </ul>

        <div style={footerStyle}>
          <p>BIT312 Project</p>
        </div>
      </div>
      
      <div style={contentStyle}>
        <Routes>
          <Route path="/" element={<RoomsPage />} />
          <Route path="/booking" element={<BookingPage />} />
          <Route path="/bookings" element={<BookingsList />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
