import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { DermAppointment } from '../dto/dermAppointment';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-derm-appointment-list',
  templateUrl: './derm-appointment-list.component.html',
  styleUrls: ['./derm-appointment-list.component.css']
})
export class DermAppointmentListComponent implements OnInit {

  appointments : any
  searchText
  endpoint = Endpoint

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };
    this.http
      .get(this.endpoint.DERM_APPOINTMENT_LIST + Global.clickedPharmacy.id,options)
      .pipe(
        map(returnedAppointments=> {
          this.appointments = returnedAppointments
          console.log(returnedAppointments)
        })
      ).subscribe()
  }

  scheduleAppointment(app: DermAppointment){

  }

}
