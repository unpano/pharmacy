import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { DermAppointment } from '../dto/dermAppointment';
import { Email } from '../dto/email';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-derm-appointment-list',
  templateUrl: './derm-appointment-list.component.html',
  styleUrls: ['./derm-appointment-list.component.css']
})
export class DermAppointmentListComponent implements OnInit {

  appointments : any
  sortedData : any
  searchText
  endpoint = Endpoint

  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    //VRACAM SLOBODNE TERMINE U APOTECI
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };
    this.http
      .get(this.endpoint.DERM_APPOINTMENT_LIST + Global.clickedPharmacy.id,options)
      .pipe(
        map(returnedAppointments=> {
          this.appointments = returnedAppointments
          this.sortedData = this.appointments.slice()
          //console.log(this.appointments)
        })
      ).subscribe()
  }

  scheduleAppointment(app: DermAppointment){
    if(Global.loggedUser.penalties < 3){
      const headers = { 
        'content-type': 'application/json',
        'Authorization': 'Bearer ' + Global.token.access_token}  
      let options = { headers: headers };
  
      let email: Email = new Email()
      email.recipient = Global.loggedUser.email
      email.subject = "Confirmation info of scheduled dermatologist appointment"
      email.message = 'You have scheduled appointment on ' + app.date.toString() + '.'
  
      this.http
      .put(this.endpoint.USER_ADD_DERM_APPOINTMENT, app.id,options)
      .pipe().subscribe(() => 
      {
        if(confirm("Successfully scheduled appointment. Information were sent to your email address.")) {
        this.http
          .post<any>(this.endpoint.SEND_EMAIL, JSON.stringify(email), options).pipe().subscribe(res => this.router.navigate(["futureDermAppointments"]))
        }
      }
      )
    }else
    alert("Number of panelties is 3. You cannot finish this action.")
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


