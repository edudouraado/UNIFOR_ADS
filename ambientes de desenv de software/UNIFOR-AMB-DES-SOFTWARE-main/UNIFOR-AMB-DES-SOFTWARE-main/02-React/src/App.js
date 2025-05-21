import React from 'react';
import './App.css';
import ContactList from './components/ContactList';

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <h1>React Contact App</h1>
                <ContactList />
            </header>
        </div>
    );
}

export default App;
