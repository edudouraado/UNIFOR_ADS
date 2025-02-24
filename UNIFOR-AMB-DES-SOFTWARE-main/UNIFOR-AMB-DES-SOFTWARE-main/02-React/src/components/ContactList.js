import React from 'react';
import Contact from './Contact';

const ContactList = () => {
  const contacts = [
    { id: 1, name: 'John Doe', email: 'john@example.com', phone: '123-456-7890' },
    { id: 2, name: 'Jane Smith', email: 'jane@example.com', phone: '098-765-4321' },
    { id: 3, name: 'Bob Johnson', email: 'bob@example.com', phone: '111-222-3333' },
  ];

  return (
    <div>
      <h2>Contact List</h2>
      {contacts.map((contact) => (
        <Contact
          key={contact.id}
          name={contact.name}
          email={contact.email}
          phone={contact.phone}
        />
      ))}
    </div>
  );
};

export default ContactList;
