import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Term } from '../dto/term';
import { TermDetailsComponent } from '../term-details/term-details.component';
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
  searchText1
  endpoint = Endpoint
  pharmAppointments: any

  constructor(private http: HttpClient,private router: Router, private dialog: MatDialog) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('token') == null){
      this.router.navigate([''])
    }
    //VRACAM PROSLE PREGLEDE PACIJENTA KOD DERMATOLOGA
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")}  
    let options = { headers: headers };
    this.http
      .get(this.endpoint.PAST_DERM_APPOINTMENT_LIST,options)
      .pipe(
        map(returnedAppointments=> {
          var datePipe = new DatePipe('en-US');
          for (const term in returnedAppointments) {
            returnedAppointments[term]["date"] = datePipe.transform(returnedAppointments[term]["date"], 'dd-MM-yyyy HH:mm');
         }
          this.appointments = returnedAppointments
          //console.log(this.appointments)
        })
      ).subscribe()

      //VRACAM PROSLE PREGLEDE PACIJENTA KOD FARMACEUTA
      this.http
      .get(this.endpoint.PAST_PHARM_APPOINTMENT_LIST,options)
      .pipe(
        map(returnedAppointments=> {
          var datePipe = new DatePipe('en-US');
          for (const term in returnedAppointments) {
            returnedAppointments[term]["start"] = datePipe.transform(returnedAppointments[term]["start"], 'dd-MM-yyyy HH:mm');
            returnedAppointments[term]["end"] = datePipe.transform(returnedAppointments[term]["end"], 'dd-MM-yyyy HH:mm');
          }
          this.pharmAppointments = returnedAppointments
          console.log(returnedAppointments)
        })
      ).subscribe()
  }

  pharmAppDetails(app1: Term){
    Global.clickedTerm = app1
    let dialogRef = this.dialog.open(TermDetailsComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
    })
    dialogRef.afterClosed().subscribe();
  }

}
