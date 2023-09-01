import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { Ticket } from '../../models/Ticket';
import { AdminServiceService} from '../admin-service.service';

@Component({
  selector: 'app-tickets-view',
  templateUrl: './tickets-view.component.html',
  styleUrls: ['./tickets-view.component.css']
})
export class TicketsViewComponent implements OnInit{

  constructor(private service: AdminServiceService){}

  @Output() ticketsViewLoadEvent = new EventEmitter<boolean>();

  ticketViewFlag: boolean = false;
  
  tickets: Ticket[] = [];

  ngOnInit(): void {
      this.ticketViewFlag = true;
      console.log('Tickets view is initiated.');
      this.service.getAllTickets().subscribe(data => this.tickets = data);
      console.log(this.tickets);
      // this.ticketsViewLoadEvent.emit(this.ticketViewFlag);
  }

}
