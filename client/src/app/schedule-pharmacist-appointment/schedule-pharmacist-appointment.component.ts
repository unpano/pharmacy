import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Endpoint } from '../util/endpoints-enum';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker'
import { Time } from '@angular/common';
import { Global } from '../util/global';
import { catchError, map } from 'rxjs/operators';
import { Pharmacy } from '../dto/pharmacy';
import { User } from '../dto/user';
import { Email } from '../dto/email';
import { Router } from '@angular/router';
import { EMPTY, Observable } from 'rxjs';

@Component({
  selector: 'app-schedule-pharmacist-appointment',
  templateUrl: './schedule-pharmacist-appointment.component.html',
  styleUrls: ['./schedule-pharmacist-appointment.component.css']
})


export class SchedulePharmacistAppointmentComponent implements OnInit {

  user: any
  dateInput
  timeInput
  pharmacies: any
  pharmacists: any
  searchText
  searchText1
  endpoint = Endpoint;
  
  constructor(public dialog: MatDialog,private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('token') == null){
      this.router.navigate([''])
    }
  }

  
  

  onViewPharmacies(){
    //console.log(this.dateInput)
    //console.log(this.timeInput)
    //poziv metode koja na osnovu datuma prolazi kroz sve radne sate farmaceuta i pronalazi slobodne termine 
    
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")} 
    let options = { headers: headers };

    this.http
      .get(this.endpoint.FREE_PHARMACIES + '/' + this.timeInput + ':00' + '/'  + this.dateInput,options)
      .pipe(
        map(returnedPharmacies => this.pharmacies = returnedPharmacies)).subscribe()
    

    
  }

  pickPharmacy(pharmacy: Pharmacy){
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")} 
    let options = { headers: headers };

    this.http
      .get(this.endpoint.FREE_PHARMACISTS + '/' + this.timeInput + ':00' + '/'  + this.dateInput +
      '/' + pharmacy.id,options)
      .pipe(
        map(returnedPharmacists => {this.pharmacists = returnedPharmacists
          console.log(returnedPharmacists)})).subscribe()
  }

  schedule(pharmacist: User){
    //kreiranje novog term-a

    //Samoako je broj penala 
    const headers1 = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")}  
    let options1 = { headers: headers1 };

    this.http
    .get<Observable<User>>(this.endpoint.USER_PROFILE,options1)
      .pipe(
        map(returnedUser => {
          this.user = returnedUser  

        })).subscribe(() =>
        {
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")}  
    let options = { headers: headers };

    let email: Email = new Email()
    email.recipient = this.user.email
    email.subject = "Confirmation info of scheduled pharmacist appointment"
    email.message = 'You have scheduled appointment for pharmacist  ' + pharmacist.firstName + '.'

    this.http
      .put(this.endpoint.SCHEDULE_PHARMACIST + pharmacist.id + '/' + this.dateInput + '/' + this.timeInput,null,options)
      .pipe(catchError((error: HttpErrorResponse) => {
        if (error.error instanceof Error) {
          alert("Bad request, please try again later.");
        } else {
          alert("Somebody scheduled this appointment in meanwhile. Please choose another one.");
          
        }
        return EMPTY;
      })).subscribe(() => 
      {
        if(confirm("Successfully scheduled appointment. Information were sent to your email address.")) {
        this.http
          .post<any>(this.endpoint.SEND_EMAIL, JSON.stringify(email), options).pipe()
          .subscribe(res => this.router.navigate(["loggedUserHomePage"]))
        }
      })})}
      
  
}
