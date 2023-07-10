// import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgApexchartsModule } from 'ng-apexcharts';
import { NgModule } from '@angular/core';
import { AgentDashboardComponent } from './agent-dashboard/agent-dashboard.component';
import { AgentsAnalyticsComponent } from './admin-dashboard/agents-view/agents-analytics/agents-analytics.component';
import { AgentsViewComponent } from './admin-dashboard/agents-view/agents-view.component';
import { TicketCardComponent } from './admin-dashboard/tickets-view/ticket-card/ticket-card.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { TicketsViewComponent } from './admin-dashboard/tickets-view/tickets-view.component';
import { CustomersViewComponent } from './admin-dashboard/customers-view/customers-view.component';
import { AgentDetailsTableComponent } from './admin-dashboard/agents-view/agent-details-table/agent-details-table.component';
import { TicketIndividualViewComponent } from './admin-dashboard/ticket-individual-view/ticket-individual-view.component';
import { CustomerCardComponent } from './admin-dashboard/customers-view/customer-card/customer-card.component';
// import { TicketResponseComponent } from './admin-dashboard/ticket-individual-view/ticket-response/ticket-response.component';
import { TicketResponsesComponent } from './admin-dashboard/ticket-individual-view/ticket-responses/ticket-responses.component';

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
    AgentDetailsTableComponent,
    TicketIndividualViewComponent,
    CustomerCardComponent,
    TicketResponsesComponent
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
