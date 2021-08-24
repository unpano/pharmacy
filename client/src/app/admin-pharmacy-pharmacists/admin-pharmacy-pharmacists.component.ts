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
  searchText
  endpoint = Endpoint;
  loggedUser: boolean = false
  

  constructor(public router: Router, public dialog: MatDialog,private http: HttpClient) { }

  ngOnInit(): void {
 
      this.http
      .get(this.endpoint.PHARMACY_PHARMACIST_LIST + Global.clickedPharmacy.id)
      .pipe(
        map(returnedPh=> {
          this.pharmacists = returnedPh
        })
      ).subscribe(res => this.pharmacists)
  
  }

}
