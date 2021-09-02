import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { DermAppointment } from '../dto/dermAppointment';
import { User } from '../dto/user';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-add-derm-app2',
  templateUrl: './add-derm-app2.component.html',
  styleUrls: ['./add-derm-app2.component.css']
})
export class AddDermApp2Component implements OnInit {

  
  dermApp : DermAppointment;
  endpoint = Endpoint;


  date
  timeInput : String

  dermatologists : any

  



  constructor(private http: HttpClient,  private router: Router) { 

    this.dermApp = new DermAppointment();
  }



  
  ngOnInit(): void {

    this.dermApp.pharmacy = Global.clickedPharmacy;



    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };
    

    this.http
      .get(this.endpoint.FIND_ALL_DERMATOLOGISTS + Global.clickedPharmacy.id, options)
      .pipe(
        map(returnedUsers=> {
          this.dermatologists = returnedUsers;
        })
      ).subscribe()
  }






  addTerm()
  {
     //kreiranje nove promocije
     const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

  
        this.timeInput = "14:40:00"

    this.http
      .post(this.endpoint.ADD_NEW_TERM + this.date + '/' + this.timeInput + '/', this.dermApp, options)
      .pipe().subscribe(returnP => 
      {


         
        if(returnP == null)
        {
          alert("Condition is not satisfied!")
        }
        else
        {
          alert("You created new free dermatology appointment!")
        }
          
      })
  }

  onSelectedDermatologist(dermatologist: User)
  {
      this.dermApp.dermatologist = dermatologist;
  }





}
