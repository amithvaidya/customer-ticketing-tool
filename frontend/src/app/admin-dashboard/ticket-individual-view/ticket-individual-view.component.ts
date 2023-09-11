import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdminServiceService } from '../admin-service.service';
import { Response } from 'src/app/models/Response';
import { Ticket } from 'src/app/models/Ticket';

@Component({
  selector: 'app-ticket-individual-view',
  templateUrl: './ticket-individual-view.component.html',
  styleUrls: ['./ticket-individual-view.component.css']
})
export class TicketIndividualViewComponent implements OnInit{


  constructor(private route: ActivatedRoute, private apiService: AdminServiceService){}
  responses: Response[] = [];
  private ticketId: string | null= "";
  ticket: Ticket = {
    id: 0,
    title: "",
    agentId: 0, // not required
    agentName: "",
    customerId: 0, //Not required
    customerName: "",
    companyName: "",
    createdTimestamp: "",
    status: "",
    priority: 0,
    priorityLabel: ""
  };
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.ticketId = params.get("id");
      this.apiService.getResponsesToTicket(this.ticketId).subscribe(data => {
        this.responses = data;
      });
      this.apiService.getTicketDetails(this.ticketId).subscribe(data => {
        this.ticket = data;
      });      
    });
    
  }

}
