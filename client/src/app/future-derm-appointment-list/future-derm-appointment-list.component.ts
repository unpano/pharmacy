import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { DermAppointment } from '../dto/dermAppointment';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-future-derm-appointment-list',
  templateUrl: './future-derm-appointment-list.component.html',
  styleUrls: ['./future-derm-appointment-list.component.css']
})
export class FutureDermAppointmentListComponent implements OnInit {
  appointments : any
  searchText
  endpoint = Endpoint

  constructor(private http: HttpClient,private router: Router) { }


  ngOnInit(): void {
    //VRACAM BUDUCE PREGLEDE PACIJENTA KOD DERMATOLOGA
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };
    this.http
      .get(this.endpoint.FUTURE_DERM_APPOINTMENT_LIST,options)
      .pipe(
        map(returnedAppointments=> {
          this.appointments = returnedAppointments
          //console.log(this.appointments)
        })
      ).subscribe()
  }

  removeAppointment(app: DermAppointment){
      const headers = { 
        'content-type': 'application/json',
        'Authorization': 'Bearer ' + Global.token.access_token}  
      let options = { headers: headers };
      this.http
        .put(this.endpoint.FREE_SCHEDULED_DERM_APPOINTMENT + app.id,null,options)
        .pipe(map(returnedApp=> {
          if(returnedApp == undefined)
            alert("Deleted appointment!")
          else
            alert("Could not cencel appointment less than 24 hours before!")
        })).subscribe()
      }

}