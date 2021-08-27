import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
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


  duration : Number;
  date : Date;  
  price : Number;

  sortedData: any
  searchText
  endpoint = Endpoint;
  pharmacy : Pharmacy

  term : DermAppointment;


  dermatologist : User

  constructor(public dialog: MatDialog,private http: HttpClient) { 


    this.pharmacy = new Pharmacy();
    this.dermatologist = new User();
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
      .get(this.endpoint.DERMATOLOGIST_LIST, options)
      .pipe(
        map(returnedDermatologists=> {
          this.dermatologists = returnedDermatologists
          this.sortedData = this.dermatologists.slice()
        })
      ).subscribe()

  }

  onSelectedPharmacy(ph: Pharmacy)
  {
      this.pharmacy = ph;
      this.term.pharmacy = ph;
      alert(this.term.pharmacy.name);
  }

  onSelectedDermatologist(derm: User)
  {
      this.dermatologist = derm;
      this.term.dermatologist = derm;
      alert(this.term.dermatologist.firstName);
  }


  pickDate(){


    this.term.date = this.date;
  }



    addTerm(){
      //kreiranje novog term-a
      const headers = { 
        'content-type': 'application/json',
        'Authorization': 'Bearer ' + Global.token.access_token}  
      let options = { headers: headers };

  
      this.http
        .post(this.endpoint.ADD_NEW_TERM,options)
        .pipe().subscribe(() => 
        {
    
          this.term.date = this.date;
          this.term.dermatologist = this.dermatologist;
          this.term.duration = 100;
          this.term.price = 1000;
          this.term.pharmacy = this.pharmacy;
          this.term.user = null;
        })
    }




}
