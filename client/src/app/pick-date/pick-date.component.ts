import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { EMPTY, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Email } from '../dto/email';
import { Reservation } from '../dto/reservation';
import { User } from '../dto/user';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-pick-date',
  templateUrl: './pick-date.component.html',
  styleUrls: ['./pick-date.component.css']
})
export class PickDateComponent implements OnInit {

  user: any
  newDate: Date
  reservation: any
  retRes: any
  endpoint = Endpoint;
  constructor(public router: Router,public dialog: MatDialog,private http: HttpClient) { }

  ngOnInit(): void {
  }

  pickDate(){
    //Prvim rezervaciju za lek
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")}  
    let options = { headers: headers };

    this.http
    .put(this.endpoint.RESERVE_MED + Global.clickedPharmacy.id + '/' + Global.medToReserve.id,JSON.stringify(this.newDate),options)
    .pipe(catchError((error: HttpErrorResponse) => {
      if (error.error instanceof Error) {
        alert("Bad request, please try again later.");
      } else {
        alert("There is no more med. Please check another pharmacy.");
        
      }
      return EMPTY;
    })).subscribe(returnedRes => {
                     
        this.retRes = returnedRes
        let email: Email = new Email()
        const headers1 = { 
          'content-type': 'application/json',
          'Authorization': 'Bearer ' + sessionStorage.getItem("token")}  
        let options1 = { headers: headers1 };
    
        this.http
        .get<Observable<User>>(this.endpoint.USER_PROFILE,options1)
          .pipe(
            map(returnedUser => {
              this.user = returnedUser  
    
            })).subscribe(() =>
            {
        email.recipient = this.user.email
        email.subject = "Confirmation info of reserved med"
        email.message = 'You have reserved med. Reservation id: ' + this.retRes.id + '.'

        if(confirm("Successfully reserved med. Information were sent to your email address.")) {
        this.http
            .post<any>(this.endpoint.SEND_EMAIL, JSON.stringify(email), options).pipe().subscribe()
            this.router.navigate(["loggedUserHomePage"]);
          }
            })
    }
    )
  }

}
