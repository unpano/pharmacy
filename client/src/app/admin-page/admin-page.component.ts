import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { map } from 'rxjs/operators';
import { Pharmacy } from '../dto/pharmacy';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';
import { PharmacyMedsComponent } from '../pharmacy-meds/pharmacy-meds.component';
import { Router } from '@angular/router';
import { PharmacyDetailsComponent } from '../pharmacy-details/pharmacy-details.component';


@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  searchText
  endpoint = Endpoint;
  
  constructor(public dialog: MatDialog,private http: HttpClient, private router: Router) { }

  ngOnInit(): void {


    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };



    if(Global.clickedMed.id != undefined){
      this.http
      .get(this.endpoint.MED_PHARMACY_LIST + Global.clickedMed.id)
      .pipe(
        map(returnedPharmacies=> {

        })
      ).subscribe( Global.clickedMed = undefined)
    }
    else
    {
      this.http
      .get(this.endpoint.GET_PHARMACY + Global.loggedUser.id , options)
      .pipe(
        map(returnedPharmacy=> {
          let pharmacy: any
          pharmacy = returnedPharmacy  
          Global.clickedPharmacy = pharmacy;


          this.router.navigate(['/pharmacyDetails'])

        })
      ).subscribe(


      )
    }
    
  }

  clickedPharmacy(pharmacy: Pharmacy){
    Global.clickedPharmacy = pharmacy


    this.router.navigate(['/pharmacyDetails'])
  }

  


}
