import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Endpoint } from '../util/endpoints-enum';

@Component({
  selector: 'app-schedule-pharmacist-appointment',
  templateUrl: './schedule-pharmacist-appointment.component.html',
  styleUrls: ['./schedule-pharmacist-appointment.component.css']
})
export class SchedulePharmacistAppointmentComponent implements OnInit {

  date = new FormControl(new Date());
  pharmacies: any
  searchText
  endpoint = Endpoint;
  constructor(public dialog: MatDialog,private http: HttpClient) { }

  ngOnInit(): void {
  }

  onViewPharmacies(){
    console.log(this.date.value)
    //poziv metode koja na osnovu datuma prolazi kroz sve radne sate farmaceuta i pronalazi slobodne termine 
  }

  pickPharmacy(){
    
  }
}
