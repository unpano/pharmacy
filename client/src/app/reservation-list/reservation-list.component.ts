import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Sort } from '@angular/material/sort';
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
  sortedData: any
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
          this.sortedData = this.reservations.slice()
          console.log(this.sortedData)
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

  pickUp(res){
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };
    this.http
      .put(this.endpoint.PICK_UP_MED + res.id,null,options)
      .pipe(map(returnedRes=> {
        if(returnedRes == true)
          alert("Medication picked up successfully!")
        
      })).subscribe()
  }

  sortData(sort: Sort) {
    const data = this.reservations.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'date': return compare(a.date, b.date, isAsc);
        case 'med': return compare(a.med.name, b.med.name, isAsc);
        case 'pharmacy': return compare(a.pharmacy.name, b.pharmacy.name, isAsc);
        default: return 0;
      }
    });
  }
 

}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}


