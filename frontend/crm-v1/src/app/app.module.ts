// import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
// import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
// import { AgentDashboardComponent } from './agent-dashboard/agent-dashboard.component';
// import { TicketCardComponent } from './admin-dashboard/ticket-card/ticket-card.component';
import { TicketAnalyticsComponent } from './admin-dashboard/tickets-view/ticket-analytics/ticket-analytics.component';
// import { AgentsViewComponent } from './admin-dashboard/agents-view/agents-view.component';
// import { AgentsAnalyticsComponent } from './admin-dashboard/agents-analytics/agents-analytics.component';
import { NgApexchartsModule } from 'ng-apexcharts';
// import { AdminDashboardModule } from './admin-dashboard/admin-dashboard.module';
import { NgModule } from '@angular/core';
import { AgentDashboardComponent } from './agent-dashboard/agent-dashboard.component';
import { AgentsAnalyticsComponent } from './admin-dashboard/agents-view/agents-analytics/agents-analytics.component';
import { AgentsViewComponent } from './admin-dashboard/agents-view/agents-view.component';
import { TicketCardComponent } from './admin-dashboard/tickets-view/ticket-card/ticket-card.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { TicketsViewComponent } from './admin-dashboard/tickets-view/tickets-view.component';
import { CustomersViewComponent } from './admin-dashboard/customers-view/customers-view.component';
import { TicketViewPanelComponent } from './admin-dashboard/tickets-view/ticket-view-panel/ticket-view-panel.component';
import { AgentDetailsTableComponent } from './admin-dashboard/agents-view/agent-details-table/agent-details-table.component';

@NgModule({
  declarations: [
    AppComponent,
    AgentDashboardComponent,
    AgentsAnalyticsComponent,
    AgentsViewComponent,
    AgentDashboardComponent,
    TicketCardComponent,
    AdminDashboardComponent,
    TicketsViewComponent,
    CustomersViewComponent,
    TicketViewPanelComponent,
    TicketAnalyticsComponent,
    AgentDetailsTableComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgApexchartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
