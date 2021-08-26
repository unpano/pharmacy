import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-medication-rate-list',
  templateUrl: './medication-rate-list.component.html',
  styleUrls: ['./medication-rate-list.component.css']
})
export class MedicationRateListComponent implements OnInit {

  meds: any
  ocena: Number
  searchText
  endpoint = Endpoint

  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    //dobavi listu lekova
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };
    
    this.http
      .get(this.endpoint.MEDS_RATE_LIST,options)
      .pipe(
        map(returnedMeds => {
          this.meds = returnedMeds
        })
      ).subscribe()
  }

  rateMed(medId: Number){
    //oceni lek
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.http
    .put(this.endpoint.RATE_MED + medId,this.ocena,options)
    .pipe().subscribe(() => {if(confirm("Successfully rated med.")) {
      this.router.navigate(["loggedUserHomePage"]);}}
    )
  }

}
