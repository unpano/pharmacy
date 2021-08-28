import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Email } from '../dto/email';
import { Reservation } from '../dto/reservation';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-pick-date',
  templateUrl: './pick-date.component.html',
  styleUrls: ['./pick-date.component.css']
})
export class PickDateComponent implements OnInit {

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
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.http
    .put(this.endpoint.RESERVE_MED + Global.clickedPharmacy.id + '/' + Global.medToReserve.id,JSON.stringify(this.newDate),options)
    .pipe().subscribe(returnedRes => {
             
        this.retRes = returnedRes
        let email: Email = new Email()
        email.recipient = Global.loggedUser.email
        email.subject = "Confirmation info of reserved med"
        email.message = 'You have reserved med. Reservation id: ' + this.retRes.id + '.'

        if(confirm("Successfully reserved med. Information were sent to your email address.")) {
        this.http
            .post<any>(this.endpoint.SEND_EMAIL, JSON.stringify(email), options).pipe().subscribe()
            this.router.navigate(["loggedUserHomePage"]);
          }
        
    }
    )
  }

}
