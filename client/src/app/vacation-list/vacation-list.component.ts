import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { EMPTY } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Email } from '../dto/email';
import { Pharmacy } from '../dto/pharmacy';
import { User } from '../dto/user';
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

  odgovor: String;

  pharmacy : Pharmacy = Global.clickedPharmacy;

  worker : any
  
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

  

  catchError((error: HttpErrorResponse) => {
    if (error.error instanceof Error) {
      // A client-side or network error occurred. Handle it accordingly.
      alert("Bad request, please try again later.");
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      alert("Already answered");
    }

    // If you want to return a new response:
    //return of(new HttpResponse({body: [{name: "Default value..."}]}));

    // If you want to return the error on the upper level:
    //return throwError(error);

    // or just return nothing:
    return EMPTY;
  }),
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

      alert("Vacation is declined")

  })).subscribe()



  if( v.approved == true)
  {
    this.odgovor = "approved!";
  }
  else
  {
    this.odgovor = "declined!";
  }


  ///potrebno je pronaci objeat pharmacist ili dermatologist kom saljemo email



  this.http
  .get(this.endpoint.FIND_WORKER + v.id, options)
  .pipe(
    map(returnedPharmacists => {
      this.worker = returnedPharmacists


    })
  ).subscribe(() => {})



    let email: Email = new Email()
    email.recipient = this.worker.email
    email.subject = "Vacation Request"
    email.message = 'Vacation is'+ this.odgovor
   
  

    if(confirm("Successfully answerd. Information were sent to your email address.")) {
    this.http
      .post<any>(this.endpoint.SEND_EMAIL, JSON.stringify(email), options).pipe()
      .subscribe(res => 
        alert("Email sent")
        )

    }
  

  }



}
