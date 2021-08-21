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
  date = new FormControl(new Date());
  reservation: any
  endpoint = Endpoint;
  constructor(public router: Router,public dialog: MatDialog,private http: HttpClient) { }

  ngOnInit(): void {
  }

  pickDate(){
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    console.log(this.date.value)
    this.http
    .put(this.endpoint.RESERVE_MED + Global.clickedPharmacy.id + '/' + Global.medToReserve.id,this.date.value,options)
    .pipe().subscribe(() => {
              
        let email: Email = new Email()
        email.recipient = Global.loggedUser.email
        email.subject = "Confirmation info of reserved med"
        email.message = 'You have reserved med. Reservation id: ' + 'ovde fali id rez' + '.'
        this.http
            .post<any>(this.endpoint.SEND_EMAIL, JSON.stringify(email), options).pipe().subscribe()
          
        this.router.navigate(["loggedUserHomePage"]);
    }
    )
  }

}
