import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdminServiceService } from '../admin-service.service';
import { Response } from 'src/app/models/Response';

@Component({
  selector: 'app-ticket-individual-view',
  templateUrl: './ticket-individual-view.component.html',
  styleUrls: ['./ticket-individual-view.component.css']
})
export class TicketIndividualViewComponent implements OnInit{


  constructor(private route: ActivatedRoute, private apiService: AdminServiceService){}
  responses: Response[] = [];
  private ticketId: string | null= "";
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.ticketId = params.get("id");
      this.apiService.getResponsesToTicket(this.ticketId).subscribe(data => {
        this.responses = data;
      });      
    });
    
  }

}
