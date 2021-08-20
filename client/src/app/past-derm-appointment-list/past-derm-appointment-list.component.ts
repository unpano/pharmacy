import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-past-derm-appointment-list',
  templateUrl: './past-derm-appointment-list.component.html',
  styleUrls: ['./past-derm-appointment-list.component.css']
})
export class PastDermAppointmentListComponent implements OnInit {
  appointments : any
  searchText
  endpoint = Endpoint

  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    //VRACAM PROSLE PREGLEDE PACIJENTA KOD DERMATOLOGA
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };
    this.http
      .get(this.endpoint.PAST_DERM_APPOINTMENT_LIST,options)
      .pipe(
        map(returnedAppointments=> {
          this.appointments = returnedAppointments
          //console.log(this.appointments)
        })
      ).subscribe()
  }

}
