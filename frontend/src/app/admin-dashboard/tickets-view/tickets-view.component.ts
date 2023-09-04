import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { Ticket } from '../../models/Ticket';
import { AdminServiceService} from '../admin-service.service';
import { Agent } from 'src/app/models/Agent';

@Component({
  selector: 'app-tickets-view',
  templateUrl: './tickets-view.component.html',
  styleUrls: ['./tickets-view.component.css']
})
export class TicketsViewComponent implements OnInit{

  constructor(private service: AdminServiceService){}
  tickets: Ticket[] = [];
  ngOnInit(): void {
      this.service.getAllTickets().subscribe(data => this.tickets = data);
  }


}
