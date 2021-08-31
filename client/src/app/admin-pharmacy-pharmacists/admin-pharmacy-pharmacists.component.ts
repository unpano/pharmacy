import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { AddPharmacistComponent } from '../add-pharmacist/add-pharmacist.component';
import { User } from '../dto/user';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-admin-pharmacy-pharmacists',
  templateUrl: './admin-pharmacy-pharmacists.component.html',
  styleUrls: ['./admin-pharmacy-pharmacists.component.css']
})
export class AdminPharmacyPharmacistsComponent implements OnInit {

  pharmacists: any
  ratedPharmacists: any
  ocena: Number
  ocena1: Number
  searchText
  searchText1
  endpoint = Endpoint
  
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
  }





  

  add()
  {
    let dialogRef = this.dialog.open(AddPharmacistComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
})
    dialogRef.afterClosed().subscribe();
  }




  deletePharmacist(pharmacist : User)
  {
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };


  this.http
  .put(this.endpoint.DELETE_PHARMACIST + pharmacist.id ,Global.clickedPharmacy, options)
  .pipe(
    map(returnedPh=> {
  
      if(returnedPh == 0)
        alert("The pharmacist does'n work in pharmacy anymore!")
      else
        alert("The pharmacist can't get fired ")

    })).subscribe()


    this.router.navigate(["adminPage"]);

  }

}
