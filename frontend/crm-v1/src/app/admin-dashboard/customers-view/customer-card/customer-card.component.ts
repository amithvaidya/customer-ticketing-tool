import { Component, Input } from '@angular/core';
import { Customer } from '../Customer';

@Component({
  selector: 'app-customer-card',
  templateUrl: './customer-card.component.html',
  styleUrls: ['./customer-card.component.css']
})
export class CustomerCardComponent {
  @Input() customer: Customer = {
    id: 0,
    name: "",
    companyName: "",
    numOfTickets: 0,
    priority: 0,
    satisfactionRating: 0,
    agentPOC: ""
  };

  showClientDetailsFlag: boolean = false;

  showClientDetails(): void {
    this.showClientDetailsFlag = true;
  }

  hideClientDetails(): void {
    this.showClientDetailsFlag = false;
  }
}
