import { Component } from '@angular/core';
import { Agent } from './Agent';
import {
  ChartComponent,
  ApexAxisChartSeries,
  ApexChart,
  ApexXAxis,
  ApexTitleSubtitle
} from "ng-apexcharts";

@Component({
  selector: 'app-agents-analytics',
  templateUrl: './agents-analytics.component.html',
  styleUrls: ['./agents-analytics.component.css']
})
export class AgentsAnalyticsComponent {
  agents: Agent[] = [
    new Agent(1, 'Amith', 3.5, 14, '1h 5m', '1h 5m'),
    new Agent(1, 'Amith', 3.5, 14, '1h 5m', '1h 5m'),
    new Agent(1, 'Amith', 3.5, 14, '1h 5m', '1h 5m')
  ];

  series: ApexAxisChartSeries = [
    {
      name: "My-series",
      data: [10, 41, 35, 51, 49, 62, 69, 91, 148]
    }
  ];

  series2: ApexAxisChartSeries = [
    {
      name: "Agent A",
      data: [10, 10, 15, 20, 25, 25, 25, 20, 10, 5]
    },

    {
      name: "Agent B",
      data: [10, 8, 5, 2, 6, 9, 12, 15, 20]
    },
    {
      name: "Agent C",
      data: [20, 25, 18, 15, 4, 15, 18, 20, 30]
    },
    
  ];

  chart: ApexChart = {
    height: 400,
    type: "bar"
  };

  chart2 : ApexChart = {
    height: 350,
    type: "line"
  }

  chart3: ApexChart = {
    height: 350,
    type: "line"
  }

  chart4: ApexChart = {
    height: 350,
    type: "line"
  }

  title1: ApexTitleSubtitle = {
    text: "No. of tickets for the week"
  };

  title2: ApexTitleSubtitle = {
    text: "No. of tickets assigned to agents"
  };
  title3: ApexTitleSubtitle = {
    text: "Average Response Time over a week"
  };
  title4: ApexTitleSubtitle = {
    text: "Average Resolution Time over a week"
  };


  xaxis: ApexXAxis = {
    categories: ["Jan", "Feb",  "Mar",  "Apr",  "May",  "Jun",  "Jul",  "Aug", "Sep"]
  };
}
