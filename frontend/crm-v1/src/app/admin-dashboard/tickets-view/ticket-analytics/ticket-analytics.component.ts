import { Component } from '@angular/core';
import {
  ChartComponent,
  ApexAxisChartSeries,
  ApexChart,
  ApexXAxis,
  ApexTitleSubtitle
} from "ng-apexcharts";

@Component({
  selector: 'app-ticket-analytics',
  templateUrl: './ticket-analytics.component.html',
  styleUrls: ['./ticket-analytics.component.css']
})
export class TicketAnalyticsComponent {

  series: ApexAxisChartSeries = [
    {
      name: "My-series",
      data: [10, 41, 35, 51, 49, 62, 69, 91, 148]
    }
  ];
  chart: ApexChart = {
    height: 350,
    type: "bar"
  };
  title: ApexTitleSubtitle = {
    text: "My First Angular Chart"
  };
  xaxis: ApexXAxis = {
    categories: ["Jan", "Feb",  "Mar",  "Apr",  "May",  "Jun",  "Jul",  "Aug", "Sep"]
  };
}
