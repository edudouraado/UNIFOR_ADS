import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContactComponent } from '../contact/contact.component';

@Component({
  selector: 'app-contact-list',
  standalone: true,
  imports: [CommonModule, ContactComponent],
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.css']
})
export class ContactListComponent {
  contacts = [
    { id: 1, name: 'John Doe', email: 'john@example.com', phone: '123-456-7890' },
    { id: 2, name: 'Jane Smith', email: 'jane@example.com', phone: '098-765-4321' },
    { id: 3, name: 'Bob Johnson', email: 'bob@example.com', phone: '111-222-3333' },
  ];
}
