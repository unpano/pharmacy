import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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

  constructor(private http: HttpClient,private router: Router) { }

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
          console.log(this.appointments)
        })
      ).subscribe()
  }

  scheduleAppointment(app: DermAppointment){
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    

    this.http
    .put(this.endpoint.USER_ADD_DERM_APPOINTMENT + Global.loggedUser.id, app.id,options)
    .pipe().subscribe(() => {if(confirm("Successfully added allergy.")) {
      this.router.navigate(["profile"]);}}
    )
  }

}
