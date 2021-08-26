import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { map } from 'rxjs/operators';
import { Pharmacy } from '../dto/pharmacy';
import { User } from '../dto/user';
import { Endpoint } from '../util/endpoints-enum';

@Component({
  selector: 'app-add-derm-appointment',
  templateUrl: './add-derm-appointment.component.html',
  styleUrls: ['./add-derm-appointment.component.css']
})
export class AddDermAppointmentComponent implements OnInit {

  pharmacies : any
  dermatologists : any


  duration : Number;
  date : Date;  
  sortedData: any
  searchText
  endpoint = Endpoint;
  pharmacy : Pharmacy

  dermatologist : User

  constructor(public dialog: MatDialog,private http: HttpClient) { 
   
  }

  ngOnInit(): void {
    this.http
      .get(this.endpoint.PHARMACY_LIST)
      .pipe(
        map(returnedPharmacies=> {
          this.pharmacies = returnedPharmacies
          this.sortedData = this.pharmacies.slice()
        })
      ).subscribe()

  }

  onSelectedPharmacy(ph: Pharmacy)
  {
      this.pharmacy = ph;
  }

  onSelectedDermatologist(derm: User)
  {
      this.dermatologist = derm;
  }


  pickDate(){


    console.log(this.date)
  }

}
