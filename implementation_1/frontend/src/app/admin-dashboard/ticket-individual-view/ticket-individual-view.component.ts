import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-ticket-individual-view',
  templateUrl: './ticket-individual-view.component.html',
  styleUrls: ['./ticket-individual-view.component.css']
})
export class TicketIndividualViewComponent implements OnInit{


  constructor(private route: ActivatedRoute){}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      var id = params.get('id');
      console.log(id);
    });
  }
}
