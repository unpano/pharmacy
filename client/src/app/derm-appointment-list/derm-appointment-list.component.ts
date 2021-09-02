import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { EMPTY, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { DermAppointment } from '../dto/dermAppointment';
import { Email } from '../dto/email';
import { User } from '../dto/user';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-derm-appointment-list',
  templateUrl: './derm-appointment-list.component.html',
  styleUrls: ['./derm-appointment-list.component.css']
})
export class DermAppointmentListComponent implements OnInit {

  user: any
  appointments : any
  sortedData : any
  searchText
  endpoint = Endpoint

  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    //VRACAM SLOBODNE TERMINE U APOTECI
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")}  
    let options = { headers: headers };

    this.http
      .get(this.endpoint.DERM_APPOINTMENT_LIST + Global.clickedPharmacy.id,options)
        .pipe(
          map(returnedAppointments=> {
            this.appointments = returnedAppointments
            this.sortedData = this.appointments.slice()
          })
        ).subscribe()
  }

  scheduleAppointment(app: DermAppointment){
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
      const headers = { 
        'content-type': 'application/json',
        'Authorization': 'Bearer ' + sessionStorage.getItem("token")}  
      let options = { headers: headers };
  
      let email: Email = new Email()
      email.recipient = this.user.email
      email.subject = "Confirmation info of scheduled dermatologist appointment"
      email.message = 'Hey, ' + this.user.firstName + ',' + 
      'You have scheduled dermatologist appointment on ' + app.date.toString() + '. ' +
      'Appointment is in ' + app.pharmacy.name + ', ' + app.pharmacy.address + ', ' + app.pharmacy.city + '.' +
      'You chosen ' + app.dermatologist.firstName + 'as your dermatologist. ' +
      'Duration of examination is ' + app.duration + ' and ' +
      'price is ' + app.price + '.'
  
      this.http
      .put(this.endpoint.USER_ADD_DERM_APPOINTMENT, app.id,options)
        .pipe(catchError((error: HttpErrorResponse) => {
          if (error.error instanceof Error) {
            alert("Bad request, please try again later.");
          } else {
            alert("Somebody scheduled this appointment in meanwhile. Please choose another one.");
            
          }
          return EMPTY;
        })).subscribe(() => 
        {
          if(confirm("Successfully scheduled appointment. Information were sent to your email address.")) {
          this.http
            .post<any>(this.endpoint.SEND_EMAIL, JSON.stringify(email), options).pipe().subscribe(res => this.router.navigate(["futureDermAppointments"]))
          }
        }
      )
        })
    
  }

  sortData(sort: Sort) {
    const data = this.appointments.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'date': return compare(a.name, b.name, isAsc);
        case 'duration': return compare(a.duration, b.duration, isAsc);
        case 'price': return compare(a.price, b.price, isAsc);
        case 'dermatologistName': return compare(a.dermatologist.firstName, b.dermatologist.firstName, isAsc);
        case 'dermatologistSurname': return compare(a.dermatologist.lastName, b.dermatologist.lastName, isAsc);
        case 'rank': return compare(a.dermatologist.rank, b.dermatologist.rank, isAsc);
        default: return 0;
      }
    });
  }
 

}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}


