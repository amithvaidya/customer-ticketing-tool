import { Component, OnInit, Input } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Agent } from '../../../models/Agent';

@Component({
  selector: 'app-agent-details-table',
  templateUrl: './agent-details-table.component.html',
  styleUrls: ['./agent-details-table.component.css']
})
export class AgentDetailsTableComponent{
  public agents: Agent[] = [];
  
  constructor(private apiService: AdminServiceService){}

  ngOnInit(): void {
    this.apiService.getAllAgents().subscribe(data => {
      this.agents = data;
      console.log(this.agents);
    });
    
  }
}
