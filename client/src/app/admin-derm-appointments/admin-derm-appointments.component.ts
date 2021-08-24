import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-admin-derm-appointments',
  templateUrl: './admin-derm-appointments.component.html',
  styleUrls: ['./admin-derm-appointments.component.css']
})
export class AdminDermAppointmentsComponent implements OnInit {

  appointments : any
  searchText
  endpoint = Endpoint

  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {

     //VRACAM SLOBODNE TERMINE U APOTECI
   const headers = { 
    'content-type': 'application/json',
    'Authorization': 'Bearer ' + Global.token.access_token}  
  let options = { headers: headers };
  this.http
    .get("http://localhost:8084/dermAppointments/" + Global.clickedPharmacy.id,options)
    .pipe(
      map(returnedAppointments=> {
        this.appointments = returnedAppointments
        console.log(this.appointments)
      })
    ).subscribe()
   
  }

}
