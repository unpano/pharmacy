import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Med } from '../dto/med';
import { Pharmacy } from '../dto/pharmacy';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-admin-pharmacy-meds',
  templateUrl: './admin-pharmacy-meds.component.html',
  styleUrls: ['./admin-pharmacy-meds.component.css']
})
export class AdminPharmacyMedsComponent implements OnInit {

  meds: any
  reserveFromPickedPharmacy: boolean = Global.reserveFromPickedPharmacy
  searchText
  endpoint = Endpoint;
  loggedUser: boolean = false

  

  constructor(public router: Router,public dialog: MatDialog,private http: HttpClient) {

   }

  ngOnInit(): void {


      this.http
      .get(this.endpoint.PHARMACY_MED_LIST + Global.clickedPharmacy.id)
      .pipe(
        map(returnedMeds=> {
          this.meds = returnedMeds
          console.log(returnedMeds)
        })
      ).subscribe()
    
  }




  deleteMed(med : Med)
  {
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

  

  this.http
  .put(this.endpoint.DELETE_MED + med.id,Global.clickedPharmacy, options)
  .pipe(
    map(returnedPh=> {
  
      if(returnedPh == 0)
        alert("Deleted medicine!")
      else
        alert("The medicine can't be removed, it's reserved")

    
     
    })).subscribe()
  }

}