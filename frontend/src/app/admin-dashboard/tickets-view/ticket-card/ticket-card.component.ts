import { Component, Input } from '@angular/core';
import { Ticket } from '../../../models/Ticket';

@Component({
  selector: 'app-ticket-card',
  templateUrl: './ticket-card.component.html',
  styleUrls: ['./ticket-card.component.css']
})
export class TicketCardComponent {
  @Input() ticket: Ticket = {
    id: 0,
    title: "",
    agentId: 0,
    customerId: 0,
    createdTimestamp: "",
    status: "",
    priority: 0
  };
  
}
