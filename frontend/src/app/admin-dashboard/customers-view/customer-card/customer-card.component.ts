import { Component, Input } from '@angular/core';
import { Customer } from '../../../models/Customer';

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
    csatRating: 0,
    agentId: 0
  };

  showClientDetailsFlag: boolean = false;

  showClientDetails(): void {
    this.showClientDetailsFlag = true;
  }

  hideClientDetails(): void {
    this.showClientDetailsFlag = false;
  }
}
