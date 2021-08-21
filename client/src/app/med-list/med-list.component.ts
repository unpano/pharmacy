import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Med } from '../dto/med';
import { PharmacyListComponent } from '../pharmacy-list/pharmacy-list.component';
import { PickDateComponent } from '../pick-date/pick-date.component';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-med-list',
  templateUrl: './med-list.component.html',
  styleUrls: ['./med-list.component.css']
})
export class MedListComponent implements OnInit {

  meds: any
  reserveFromPickedPharmacy: boolean = Global.reserveFromPickedPharmacy
  searchText
  endpoint = Endpoint;
  loggedUser: boolean = false
  

  constructor(public router: Router,public dialog: MatDialog,private http: HttpClient) { }

  ngOnInit(): void {
    if(Global.loggedUser.id != undefined)
      this.loggedUser = true
   
    if(Global.allMeds == true){
      this.http
      .get(this.endpoint.MED_LIST)
      .pipe(
        map(returnedMeds=> {
          this.meds = returnedMeds
        })
      ).subscribe(res=> Global.allMeds = false)
    }else{
      this.http
      .get(this.endpoint.PHARMACY_MED_LIST + Global.clickedPharmacy.id)
      .pipe(
        map(returnedMeds=> {
          this.meds = returnedMeds
          console.log(returnedMeds)
        })
      ).subscribe()
    }
    
  }

  viewPharmacies(med: Med){
    Global.clickedMed = med
    let dialogRef = this.dialog.open(PharmacyListComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
})
    dialogRef.afterClosed().subscribe();
  }

  reserveMed(med: Med){
    //imam u globalu koja je kliknuta apoteka
    Global.medToReserve = med
    let dialogRef = this.dialog.open((PickDateComponent),{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
  })
    dialogRef.afterClosed().subscribe();


    
  }

}
