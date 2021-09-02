import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EMPTY } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-medication-rate-list',
  templateUrl: './medication-rate-list.component.html',
  styleUrls: ['./medication-rate-list.component.css']
})
export class MedicationRateListComponent implements OnInit {

  meds: any
  ratedMeds: any
  ocena: Number
  ocena1: Number
  searchText
  searchText1
  endpoint = Endpoint

  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('token') == null){
      this.router.navigate([''])
    }
    //dobavi listu lekova
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")}  
    let options = { headers: headers };
    
    this.http
      .get(this.endpoint.MEDS_RATE_LIST,options)
      .pipe(
        map(returnedMeds => {
          this.meds = returnedMeds
        })
      ).subscribe(() => {
        //nadjem vec ocenjene lekove
            this.http
          .get(this.endpoint.MEDS_RATE_LIST + '/rated',options)
          .pipe(
            map(returnedMeds => {
              this.ratedMeds = returnedMeds
            })
          ).subscribe()
      })
  }

  rateMed(medId: Number){
    //oceni lek
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")}  
    let options = { headers: headers };

    this.http
    .put(this.endpoint.RATE_MED + medId,this.ocena,options)
    .pipe(catchError((error: HttpErrorResponse) => {
      if (error.error instanceof Error) {
        alert("Bad request, please try again later.");
      } else {
        alert("You`ve already rated this med.");
        
      }
      return EMPTY;
    })).subscribe(() => {if(confirm("Successfully rated med.")) {
      this.router.navigate(["loggedUserHomePage"]);}}
    )
  }
  changeRate(medId: Number){
    
    //promeni ocenu dermatologa
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")}  
    let options = { headers: headers };

    this.http
    .put(this.endpoint.CHANGE_RATE + medId + '/MED',this.ocena1,options)
    .pipe().subscribe(() => {if(confirm("Successfully changed rate.")) {
      this.router.navigate(["loggedUserHomePage"]);}}
    )
  }

}
