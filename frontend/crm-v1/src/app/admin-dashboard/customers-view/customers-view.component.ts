import { Component } from '@angular/core';
import { Customer } from './Customer';

@Component({
  selector: 'app-customers-view',
  templateUrl: './customers-view.component.html',
  styleUrls: ['./customers-view.component.css']
})
export class CustomersViewComponent {
  customers: Customer[] = [
    {
      id: 1,
      name: "Rakesh",
      companyName: "Reliance",
      priority: 1,
      numOfTickets: 10,
      satisfactionRating: 3.5,
      agentPOC: "Amith"
    },
    {
      id: 1,
      name: "Rakesh",
      companyName: "Reliance",
      priority: 1,
      numOfTickets: 10,
      satisfactionRating: 3.5,
      agentPOC: "Amith"
    },
    {
      id: 1,
      name: "Rakesh",
      companyName: "Reliance",
      priority: 1,
      numOfTickets: 10,
      satisfactionRating: 3.5,
      agentPOC: "Amith"
    }
  ];
}
