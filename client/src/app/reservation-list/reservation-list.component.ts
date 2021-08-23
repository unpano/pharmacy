import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Reservation } from '../dto/reservation';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {
  reservations: any
  searchText
  endpoint = Endpoint;
  constructor(public router: Router,public dialog: MatDialog,private http: HttpClient) { }

  ngOnInit(): void {
    //poziv metode koja vraca sve rezervacije user-a
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };
    this.http
      .get(this.endpoint.USER_RESERVATIONS,options)
      .pipe(
        map(returnedReservations=> {
          this.reservations = returnedReservations
        })
      ).subscribe()
  }

  removeReservation(res: Reservation){
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };
    this.http
      .put(this.endpoint.FREE_RESERVED_MED + res.id,null,options)
      .pipe(map(returnedRes=> {
        if(returnedRes == undefined)
          alert("Deleted reservation!")
        else
          alert("Could not cencel reservation less than 24 hours before pickup date!")
      })).subscribe()
  }

}
