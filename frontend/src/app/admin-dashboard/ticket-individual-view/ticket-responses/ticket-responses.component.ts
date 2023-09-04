import { Component, Input } from '@angular/core';
import { Response } from 'src/app/models/Response';

@Component({
  selector: 'app-ticket-responses',
  templateUrl: './ticket-responses.component.html',
  styleUrls: ['./ticket-responses.component.css']
})
export class TicketResponsesComponent {

 @Input() ticketResponses: Response[] = [];
}
