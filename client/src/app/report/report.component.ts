import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Pharmacy } from '../dto/pharmacy';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';
    

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {


  meds : any
  pharmacists: any
  dermatologists : any
  endpoint = Endpoint

  pharmacy : Pharmacy = Global.clickedPharmacy;
  
  constructor(private http: HttpClient,private router: Router, public dialog: MatDialog) { }



  
  ngOnInit(): void {


     //dobavi listu farmaceuta
     const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.http
      .get(this.endpoint.FIND_ALL_PHARMACISTS + Global.clickedPharmacy.id, options)
      .pipe(
        map(returnedPharmacists => {
          this.pharmacists = returnedPharmacists
        })
      ).subscribe(() => {})



      this.http
      .get(this.endpoint.FIND_ALL_DERMATOLOGISTS + Global.clickedPharmacy.id, options)
      .pipe(
        map(returnedDermatologists => {
          this.dermatologists = returnedDermatologists
        })
      ).subscribe(() => {})

      


    this.http
      .get(this.endpoint.PHARMACY_MED_LIST + Global.clickedPharmacy.id, options)
      .pipe(
        map(returnedPharmacists => {
          this.meds = returnedPharmacists
        })
      ).subscribe(() => {})




  }

}
