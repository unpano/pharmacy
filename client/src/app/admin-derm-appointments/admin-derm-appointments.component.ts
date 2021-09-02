import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { AddDermApp2Component } from '../add-derm-app2/add-derm-app2.component';
import { AddDermAppointmentComponent } from '../add-derm-appointment/add-derm-appointment.component';
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

  constructor(private http: HttpClient,private router: Router,public dialog: MatDialog) { }

  ngOnInit(): void {

     //VRACAM SLOBODNE TERMINE U APOTECI
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




  addDermAppointment(){
    
    let dialogRef = this.dialog.open(AddDermApp2Component,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
})
    dialogRef.afterClosed().subscribe();
    
  }

}
