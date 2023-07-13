import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AgentDashboardComponent } from './agent-dashboard/agent-dashboard.component';
import { AgentsAnalyticsComponent } from './admin-dashboard/agents-view/agents-analytics/agents-analytics.component';
import { TicketsViewComponent } from './admin-dashboard/tickets-view/tickets-view.component';
import { AgentsViewComponent } from './admin-dashboard/agents-view/agents-view.component';
import { CustomersViewComponent } from './admin-dashboard/customers-view/customers-view.component';
import { TicketIndividualViewComponent } from './admin-dashboard/ticket-individual-view/ticket-individual-view.component';
import { CustomerDashboardComponent } from './customer-dashboard/customer-dashboard.component';
import { CreateTicketComponent } from './customer-dashboard/create-ticket/create-ticket.component';


const routes: Routes = [
  {path:'admin', component: AdminDashboardComponent,
    children: [
      {path: '', component: TicketsViewComponent},
      {path: 'tickets', component: TicketsViewComponent},
      {path: 'agents', component: AgentsViewComponent},
      {path: 'customers', component: CustomersViewComponent},
      {path: 'view-ticket/:id', component: TicketIndividualViewComponent}
      
    ]
  },
  {path:'agent', component: AgentDashboardComponent},
  {path:'agent-analytics', component: AgentsAnalyticsComponent},
  {
    path:'customer', 
    component: CustomerDashboardComponent,
    children: [
      {path: '', component: TicketsViewComponent},
      {path: 'view-tickets', component: TicketsViewComponent},
      {path: 'create-ticket', component: CreateTicketComponent}
    ]
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
