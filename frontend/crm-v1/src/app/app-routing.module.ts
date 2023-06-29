import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AgentDashboardComponent } from './agent-dashboard/agent-dashboard.component';
import { TicketCardComponent } from './admin-dashboard/tickets-view/ticket-card/ticket-card.component';
import { AgentsAnalyticsComponent } from './admin-dashboard/agents-view/agents-analytics/agents-analytics.component';
import { TicketsViewComponent } from './admin-dashboard/tickets-view/tickets-view.component';
import { AgentsViewComponent } from './admin-dashboard/agents-view/agents-view.component';
import { CustomersViewComponent } from './admin-dashboard/customers-view/customers-view.component';
import { TicketAnalyticsComponent } from './admin-dashboard/tickets-view/ticket-analytics/ticket-analytics.component';
import { TicketViewPanelComponent } from './admin-dashboard/tickets-view/ticket-view-panel/ticket-view-panel.component';

const routes: Routes = [
  {path:'admin', component: AdminDashboardComponent,
    children: [
      {path: '', component: TicketsViewComponent},
      {path: 'tickets', component: TicketsViewComponent , children: [
        {path: '', component: TicketViewPanelComponent},
        {path: 'tickets-panel', component: TicketViewPanelComponent},
        {path: 'tickets-analytics', component: TicketAnalyticsComponent},
        {path: 'tickets-categories', component: TicketAnalyticsComponent}
        
      ]},
      {path: 'agents', component: AgentsViewComponent},
      {path: 'customers', component: CustomersViewComponent},
      
    ]
  },
  {path:'agent', component: AgentDashboardComponent},
  {path:'agent-analytics', component: AgentsAnalyticsComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
