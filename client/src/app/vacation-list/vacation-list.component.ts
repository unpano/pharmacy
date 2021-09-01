import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Pharmacy } from '../dto/pharmacy';
import { Vacation } from '../dto/vacation';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-vacation-list',
  templateUrl: './vacation-list.component.html',
  styleUrls: ['./vacation-list.component.css']
})
export class VacationListComponent implements OnInit {

  
  vacations: any

  searchText

  endpoint = Endpoint

  name : any

  pharmacy : Pharmacy = Global.clickedPharmacy;
  
  constructor(private http: HttpClient,private router: Router, public dialog: MatDialog) { }

  ngOnInit(): void {
    //dobavi listu farmaceuta
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.http
      .get(this.endpoint.FIND_ALL_VACATIONS + Global.clickedPharmacy.id, options)
      .pipe(
        map(returnedPharmacists => {
          this.vacations = returnedPharmacists

        })
      ).subscribe(() => {})




  }

  getFirstName(v : Vacation)
  {

    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };


    this.http
    .get(this.endpoint.FIND_WORKER_NAMES + v.id, options)
    .pipe(
      map(returnedPharmacists => {


      })
    ).subscribe(() => {})


  }




  approve(v : Vacation)
  {
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };


this.http
.put(this.endpoint.APPROVE ,v, options)
.pipe(
  map(returnedPh=> {

      alert("Vacation is approved")

  })).subscribe()

  }

  decline(v : Vacation)
  {
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };


this.http
.put(this.endpoint.DECLINE ,v, options)
.pipe(
  map(returnedPh=> {

      alert("Vacation is declined")

  })).subscribe()

  }



}
