import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Form } from "../dto/form";
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { IssuanceRegime } from '../dto/issuanceRegime';
import { Med } from '../dto/med';
import { Pharmacy } from '../dto/pharmacy';
import { User } from '../dto/user';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';
import { EditMedComponent } from '../edit-med/edit-med.component';
import { AddMedComponent } from '../add-med/add-med.component';

@Component({
  selector: 'app-admin-pharmacy-meds',
  templateUrl: './admin-pharmacy-meds.component.html',
  styleUrls: ['./admin-pharmacy-meds.component.css']
})
export class AdminPharmacyMedsComponent implements OnInit {

  meds: any
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









  add()
  {
    let dialogRef = this.dialog.open(AddMedComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
      })
  

    dialogRef.afterClosed().subscribe();

    this.router.navigate(["adminPage"]);


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


    this.router.navigate(["adminPage"]);

  }




  update(med : Med)
  {
    Global.clickedMed = med


    let dialogRef = this.dialog.open(EditMedComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
      })
    dialogRef.afterClosed().subscribe();

    this.router.navigate(["adminPage"]);

  }



}