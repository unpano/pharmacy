import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
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
  
  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    //dobavi listu farmaceuta
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.http
      .get(this.endpoint.PHARMACIST_LIST,options)
      .pipe(
        map(returnedPharmacists => {
          this.pharmacists = returnedPharmacists
        })
      ).subscribe(() => {
        //nadjem vec ocenjene farmaceute
          this.http
        .get(this.endpoint.PHARMACIST_LIST + '/rated' ,options)
        .pipe(
          map(returnedDermatologists=> {
            this.ratedPharmacists = returnedDermatologists
          })
        ).subscribe()
})
  }

}
