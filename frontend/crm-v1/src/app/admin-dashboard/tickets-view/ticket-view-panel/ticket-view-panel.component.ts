import { Component } from '@angular/core';

@Component({
  selector: 'app-ticket-view-panel',
  templateUrl: './ticket-view-panel.component.html',
  styleUrls: ['./ticket-view-panel.component.css']
})
export class TicketViewPanelComponent {
  tickets: any[] = [
    1,1,1,1,1
  ];
}
