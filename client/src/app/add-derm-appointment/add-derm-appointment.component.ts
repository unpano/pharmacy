import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { DermAppointment } from '../dto/dermAppointment';
import { Email } from '../dto/email';
import { Pharmacy } from '../dto/pharmacy';
import { User } from '../dto/user';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-add-derm-appointment',
  templateUrl: './add-derm-appointment.component.html',
  styleUrls: ['./add-derm-appointment.component.css']
})
export class AddDermAppointmentComponent implements OnInit {

  pharmacies : any
  dermatologists : any


  sortedData: any
  searchText
  endpoint = Endpoint;

  term : DermAppointment;


  date 
  time 


  constructor(public dialog: MatDialog,private http: HttpClient,public router: Router) { 


    this.term = new DermAppointment();

    
   
  }

  ngOnInit(): void {



    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };
    
    this.http
      .get(this.endpoint.PHARMACY_LIST)
      .pipe(
        map(returnedPharmacies=> {
          this.pharmacies = returnedPharmacies
          this.sortedData = this.pharmacies.slice()
        })
      ).subscribe()

      this.http
      .get(this.endpoint.FIND_ALL_DERMATOLOGISTS+ Global.clickedPharmacy.id, options)
      .pipe(
        map(returnedDermatologists=> {
          this.dermatologists = returnedDermatologists
          this.sortedData = this.dermatologists.slice()
        })
      ).subscribe()

  }


  onSelectedDermatologist(derm: User)
  {
      this.term.dermatologist = derm;

  }





    addTerm(){
      //kreiranje novog term-a
      const headers = { 
        'content-type': 'application/json',
        'Authorization': 'Bearer ' + Global.token.access_token}  
      let options = { headers: headers };

      alert(this.time)
      this.term.duration = 100;
      this.term.price = 1000;
      this.term.user = null;
      this.term.pharmacy = Global.clickedPharmacy;


      this.http
        .post(this.endpoint.ADD_NEW_TERM + this.date + '/' + this.time, this.term, options)
        .pipe().subscribe(returnTerm => 
        {


          if (returnTerm == null)
            alert("Dermatologist already has an appointment. Try with another date!")
          else
            alert("You created new free dermatology appointment!")
        })
    }




}
