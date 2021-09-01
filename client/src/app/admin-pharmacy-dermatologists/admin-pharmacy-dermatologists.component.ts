import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { AddDermatologistComponent } from '../add-dermatologist/add-dermatologist.component';
import { Pharmacy } from '../dto/pharmacy';
import { User } from '../dto/user';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-admin-pharmacy-dermatologists',
  templateUrl: './admin-pharmacy-dermatologists.component.html',
  styleUrls: ['./admin-pharmacy-dermatologists.component.css']
})
export class AdminPharmacyDermatologistsComponent implements OnInit {

  dermatologists: any
  ratedPharmacists: any
  ocena: Number
  ocena1: Number
  searchText
  searchText1
  endpoint = Endpoint

  pharmacy : Pharmacy = Global.clickedPharmacy;

  pharmacies : any
  
  constructor(private http: HttpClient,private router: Router, public dialog: MatDialog) { }

  ngOnInit(): void {
    //dobavi listu farmaceuta
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.http
      .get(this.endpoint.FIND_ALL_DERMATOLOGISTS + Global.clickedPharmacy.id, options)
      .pipe(
        map(returnedDermatologists => {
          this.dermatologists = returnedDermatologists
        })
      ).subscribe(() => {})


  }



  

  add()
  {
    let dialogRef = this.dialog.open(AddDermatologistComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
})
    dialogRef.afterClosed().subscribe();
  }


  
  deleteDermatologist(dermatologist : User)
  {
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };


  this.http
  .put(this.endpoint.DELETE_DERMATOLOGIST + dermatologist.id ,Global.clickedPharmacy, options)
  .pipe(
    map(returnedPh=> {
  
      if(returnedPh == 0)
        alert("The dermatologist does'n work in pharmacy anymore!")
      else
        alert("The dermatologist can't get fired ")

    })).subscribe()


    this.router.navigate(["adminPage"]);

  }

}
