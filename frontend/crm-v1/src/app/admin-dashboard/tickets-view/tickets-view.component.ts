import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { Ticket } from './Ticket';

@Component({
  selector: 'app-tickets-view',
  templateUrl: './tickets-view.component.html',
  styleUrls: ['./tickets-view.component.css']
})
export class TicketsViewComponent implements OnInit{

  @Output() ticketsViewLoadEvent = new EventEmitter<boolean>();

  ticketViewFlag: boolean = false;

  ngOnInit(): void {
      this.ticketViewFlag = true;
      this.ticketsViewLoadEvent.emit(this.ticketViewFlag);
      console.log('Tickets view is initiated.');
  }



  tickets: Ticket[] = [
    {
      id: 1,
      title: "Title for 1st ticket",
      handledBy: "Amith",
      createdDate: "23-June-2023",
      status: "Resolved",
      priority: "High"
    },

    {
      id: 1,
      title: "Title for 2nd ticket",
      handledBy: "Amith",
      createdDate: "23-June-2023",
      status: "Open",
      priority: "Low"
    },
    {
      id: 2,
      title: "Title for 3rd ticket",
      handledBy: "Amith",
      createdDate: "23-June-2023",
      status: "Open",
      priority: "Low"
    },
    {
      id: 3,
      title: "Title for 3rd ticket",
      handledBy: "Amith",
      createdDate: "23-June-2023",
      status: "Pending",
      priority: "Medium"
    },
    {
      id: 4,
      title: "Title for 3rd ticket",
      handledBy: "Amith",
      createdDate: "23-June-2023",
      status: "Pending",
      priority: "High"
    },
    {
      id: 5,
      title: "Title for 3rd ticket",
      handledBy: "Amith",
      createdDate: "23-June-2023",
      status: "Open",
      priority: "High"
    },
    {
      id: 1,
      title: "Title for 3rd ticket",
      handledBy: "Amith",
      createdDate: "23-June-2023",
      status: "Open",
      priority: "High"
    },
    {
      id: 1,
      title: "Title for 3rd ticket",
      handledBy: "Amith",
      createdDate: "23-June-2023",
      status: "Open",
      priority: "High"
    },
    {
      id: 1,
      title: "Title for 3rd ticket",
      handledBy: "Amith",
      createdDate: "23-June-2023",
      status: "Open",
      priority: "High"
    },
    {
      id: 1,
      title: "Title for 3rd ticket",
      handledBy: "Amith",
      createdDate: "23-June-2023",
      status: "Open",
      priority: "High"
    },
    {
      id: 1,
      title: "Title for 3rd ticket",
      handledBy: "Amith",
      createdDate: "23-June-2023",
      status: "Open",
      priority: "High"
    },
    {
      id: 1,
      title: "Title for 3rd ticket",
      handledBy: "Amith",
      createdDate: "23-June-2023",
      status: "Open",
      priority: "High"
    },
    {
      id: 1,
      title: "Title for 3rd ticket",
      handledBy: "Amith",
      createdDate: "23-June-2023",
      status: "Open",
      priority: "High"
    },
    {
      id: 1,
      title: "Title for 3rd ticket",
      handledBy: "Amith",
      createdDate: "23-June-2023",
      status: "Open",
      priority: "High"
    },
    {
      id: 1,
      title: "Title for 3rd ticket",
      handledBy: "Amith",
      createdDate: "23-June-2023",
      status: "Open",
      priority: "High"
    },
    
  ];
}
