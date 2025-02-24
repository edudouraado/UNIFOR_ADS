const contacts = [
    { id: 1, name: 'John Doe', email: 'john@example.com', phone: '123-456-7890' },
    { id: 2, name: 'Jane Smith', email: 'jane@example.com', phone: '098-765-4321' },
    { id: 3, name: 'Bob Johnson', email: 'bob@example.com', phone: '111-222-3333' }
];

function displayContacts() {
    const contactList = document.getElementById('contact-list');

    contacts.forEach(contact => {
        const contactElement = document.createElement('div');
        contactElement.classList.add('contact');

        contactElement.innerHTML = `
        <h3>${contact.name}</h3>
        <p>Email: ${contact.email}</p>
        <p>Phone: ${contact.phone}</p>
      `;

        contactList.appendChild(contactElement);
    });
}

displayContacts();
