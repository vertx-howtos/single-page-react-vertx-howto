import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import logo from '../logo.svg';
import './App.css';
import Greeter from './Greeter';

const App: React.FC = () => (
  <Router>
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit
          {' '}
          <code>src/App.tsx</code>
          {' '}
          and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
            Learn React
        </a>
        <Greeter />
      </header>
    </div>
  </Router>
);

export default App;
