import { Component } from '@angular/core';
import { Customer } from '../../models/Customer';
import { AdminServiceService } from '../admin-service.service';

@Component({
  selector: 'app-customers-view',
  templateUrl: './customers-view.component.html',
  styleUrls: ['./customers-view.component.css']
})
export class CustomersViewComponent {
  customers: Customer[] = [];

  // customers: Customer[] = [];
  // constructor(private service: AdminServiceService){}
  // ngOnInit(): void {
  //     console.log('Tickets view is initiated.');
  //     this.service.getAllTickets().subscribe(data => this.customers = data);
  //     console.log(this.tickets);
  //     // this.ticketsViewLoadEvent.emit(this.ticketViewFlag);
  // }
}
