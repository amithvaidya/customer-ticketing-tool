import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AgentDashboardComponent } from './agent-dashboard/agent-dashboard.component';
import { TicketCardComponent } from './admin-dashboard/ticket-card/ticket-card.component';

const routes: Routes = [
  {path:'admin', component: AdminDashboardComponent,
    children: [
//      {path: 'ticket-card', component: TicketCardComponent}
    ]
  },
  {path:'agent', component: AgentDashboardComponent},
  {path:'ticket-card1', component: TicketCardComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
