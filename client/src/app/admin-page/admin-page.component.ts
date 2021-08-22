import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { map } from 'rxjs/operators';
import { Pharmacy } from '../dto/pharmacy';
import { PharmacyProfileComponent } from '../pharmacy-profile/pharmacy-profile.component';
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

  pharmacies: any
  searchText
  endpoint = Endpoint;
  
  constructor(public dialog: MatDialog,private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    if(Global.clickedMed.id != undefined){
      this.http
      .get(this.endpoint.MED_PHARMACY_LIST + Global.clickedMed.id)
      .pipe(
        map(returnedPharmacies=> {
          this.pharmacies = returnedPharmacies
        })
      ).subscribe( Global.clickedMed = undefined)
    }else{
      this.http
      .get(this.endpoint.PHARMACY_LIST)
      .pipe(
        map(returnedPharmacies=> {
          this.pharmacies = returnedPharmacies
        })
      ).subscribe()
    }
    
  }

  clickedPharmacy(pharmacy: Pharmacy){
    Global.clickedPharmacy = pharmacy
    let dialogRef = this.dialog.open(PharmacyDetailsComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
              })
    dialogRef.afterClosed().subscribe();
  }


}
