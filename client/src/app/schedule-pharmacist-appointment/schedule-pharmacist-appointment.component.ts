import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Endpoint } from '../util/endpoints-enum';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker'
import { Time } from '@angular/common';
import { Global } from '../util/global';
import { map } from 'rxjs/operators';
import { Pharmacy } from '../dto/pharmacy';
import { User } from '../dto/user';
import { Email } from '../dto/email';
import { Router } from '@angular/router';

@Component({
  selector: 'app-schedule-pharmacist-appointment',
  templateUrl: './schedule-pharmacist-appointment.component.html',
  styleUrls: ['./schedule-pharmacist-appointment.component.css']
})


export class SchedulePharmacistAppointmentComponent implements OnInit {

  dateInput
  timeInput
  pharmacies: any
  pharmacists: any
  searchText
  searchText1
  endpoint = Endpoint;
  
  constructor(public dialog: MatDialog,private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
  }

  
  

  onViewPharmacies(){
    //console.log(this.dateInput)
    //console.log(this.timeInput)
    //poziv metode koja na osnovu datuma prolazi kroz sve radne sate farmaceuta i pronalazi slobodne termine 
    
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token} 
    let options = { headers: headers };

    this.http
      .get(this.endpoint.FREE_PHARMACIES + '/' + this.timeInput + ':00' + '/'  + this.dateInput,options)
      .pipe(
        map(returnedPharmacies => this.pharmacies = returnedPharmacies)).subscribe()
    

    
  }

  pickPharmacy(pharmacy: Pharmacy){
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token} 
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
    if(Global.loggedUser.penalties < 3){
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    let email: Email = new Email()
    email.recipient = Global.loggedUser.email
    email.subject = "Confirmation info of scheduled pharmacist appointment"
    email.message = 'You have scheduled appointment for pharmacist  ' + pharmacist.firstName + '.'

    this.http
      .put(this.endpoint.SCHEDULE_PHARMACIST + pharmacist.id + '/' + Global.loggedUser.id
      + '/' + this.dateInput + '/' + this.timeInput,null,options)
      .pipe().subscribe(() => 
      {
        if(confirm("Successfully scheduled appointment. Information were sent to your email address.")) {
        this.http
          .post<any>(this.endpoint.SEND_EMAIL, JSON.stringify(email), options).pipe()
          .subscribe(res => this.router.navigate(["loggedUserHomePage"]))
        }
      })
  }else
  alert("Number of panelties is 3. You cannot finish this action.")}
}
