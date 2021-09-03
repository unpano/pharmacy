import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { EMPTY } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Offer } from '../dto/offer';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-offer-list',
  templateUrl: './offer-list.component.html',
  styleUrls: ['./offer-list.component.css']
})
export class OfferListComponent implements OnInit {

  
  
  offers: any
  searchText
  endpoint = Endpoint

  odgovor : String

  
  constructor(private http: HttpClient,public router: Router, public dialog: MatDialog) { }

  ngOnInit(): void {


    //dobavi listu farmaceuta
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.http
      .get(this.endpoint.FIND_ALL_OFFERS + Global.clickedOrder.id, options)
      .pipe(
        map(returnedPharmacists => {
          this.offers = returnedPharmacists
        })
      ).subscribe(() => {})
  }






  approve(o : Offer)
  {
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };


this.http
.put(this.endpoint.ACCEPT ,o, options)
.pipe(

  catchError((error: HttpErrorResponse) => {
    if (error.error instanceof Error) 
    {
    alert("Bad request, please try again later.");
    } 
    else 
    {
      alert("Already answered");
    }

    return EMPTY;
  }),
  map(returnedPh=> {

      alert("Offere is accepted!")

  })).subscribe()

  }

  decline(o : Offer)
  {
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };


this.http
.put(this.endpoint.NOT_ACCEPT ,o, options)
.pipe(
  
  catchError((error: HttpErrorResponse) => {
    if (error.error instanceof Error) 
    {
      alert("Bad request, please try again later.");
    }
    else 
    {
      alert("Already answered");
    }

    return EMPTY;
  }),
  map(returnedPh=> {

      alert("Offer is not accepted")

  })).subscribe()



  if( o.accepted == true)
  {
    this.odgovor = "accepted!";
  }
  else
  {
    this.odgovor = "declined!";
  }


  }

}
