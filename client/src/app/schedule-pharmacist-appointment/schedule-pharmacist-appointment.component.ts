import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Endpoint } from '../util/endpoints-enum';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker'
import { Time } from '@angular/common';
import { Global } from '../util/global';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-schedule-pharmacist-appointment',
  templateUrl: './schedule-pharmacist-appointment.component.html',
  styleUrls: ['./schedule-pharmacist-appointment.component.css']
})


export class SchedulePharmacistAppointmentComponent implements OnInit {

  dateInput
  timeInput
  pharmacies: any
  searchText
  
  endpoint = Endpoint;
  
  constructor(public dialog: MatDialog,private http: HttpClient) { }

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
      .put(this.endpoint.FREE_PHARMACIES + '/' + this.timeInput + ':00' + '/'  + this.dateInput,null,options)
      .pipe(
        map(returnedPharmacies => this.pharmacies = returnedPharmacies)).subscribe()
    

        console.log(this.pharmacies)
    
  }

  pickPharmacy(){
    
  }
}
