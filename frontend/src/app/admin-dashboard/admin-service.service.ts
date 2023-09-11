import { Injectable } from '@angular/core';
// import { catchError } from 'rxjs/operators';
// import { throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpParamsOptions } from '@angular/common/http'
import { Ticket } from '../models/Ticket';
import { Agent } from '../models/Agent';
import { Response } from '../models/Response';
@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  constructor(private httpClient: HttpClient){}
  private baseUrl = 'http://localhost:8080/admin'
  private getTicketsUrl =  this.baseUrl+'/tickets';
  private getAgentsUrl = this.baseUrl+'/agents';
  private getResponseUrl = this.baseUrl+'/responses?ticketId='
  private getTicketUrl = this.baseUrl+'/ticket?ticketId='
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type':'application/json'})
  }
  getAllTickets(){
    return this.httpClient.get<Ticket[]>(this.getTicketsUrl, this.httpOptions);
  }

  getAllAgents(){
    return this.httpClient.get<Agent[]>(this.getAgentsUrl, this.httpOptions);
  }

  getResponsesToTicket(ticketId: string|null){
    return this.httpClient.get<Response[]>(this.getResponseUrl + ticketId, this.httpOptions);
  }

  getTicketDetails(ticketId: string|null){
    return this.httpClient.get<Ticket>(this.getTicketUrl+ticketId, this.httpOptions);
  }

}
