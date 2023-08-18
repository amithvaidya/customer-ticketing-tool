import { Component } from '@angular/core';

@Component({
  selector: 'app-ticket-responses',
  templateUrl: './ticket-responses.component.html',
  styleUrls: ['./ticket-responses.component.css']
})
export class TicketResponsesComponent {

  ticketResponses: any = [
    {
      id: 1,
      responderType: "agent",
      responderName: "Amith",
      response: "This is a response from agent",
      createdTime: "19-June-2023T12:45:05"
    },
    {
      id: 2,
      responderType: "client",
      responderName: "Suresh",
      response: "This is a response from agent",
      createdTime: "19-June-2023T13:45:05"
    },
    {
      id: 3,
      responderType: "agent",
      responderName: "Amith",
      response: "This is a response from agent",
      createdTime: "19-June-2023T14:45:05"
    },
    {
      id: 4,
      responderType: "agent",
      responderName: "Amith",
      response: "This is a response from agent",
      createdTime: "19-June-2023T15:45:05"
    }
  ];
}
