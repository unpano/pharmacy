import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-pharmacist-list',
  templateUrl: './pharmacist-list.component.html',
  styleUrls: ['./pharmacist-list.component.css']
})
export class PharmacistListComponent implements OnInit {
  
  pharmacists: any
  ratedPharmacists: any
  ocena: Number
  ocena1: Number
  searchText
  searchText1
  endpoint = Endpoint
  
  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    //dobavi listu farmaceuta
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.http
      .get(this.endpoint.PHARMACIST_LIST,options)
      .pipe(
        map(returnedPharmacists => {
          this.pharmacists = returnedPharmacists
        })
      ).subscribe(() => {
        //nadjem vec ocenjene farmaceute
          this.http
        .get(this.endpoint.PHARMACIST_LIST + '/rated' ,options)
        .pipe(
          map(returnedDermatologists=> {
            this.ratedPharmacists = returnedDermatologists
          })
        ).subscribe()
})
  }

  ratePharmacist(pharmacistId: Number){
    //oceni farmaceuta
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.http
    .put(this.endpoint.RATE_PHARMACIST + pharmacistId,this.ocena,options)
    .pipe().subscribe(() => {if(confirm("Successfully rated pharmacist.")) {
      this.router.navigate(["loggedUserHomePage"]);}}
    )
  }
  changeRate(pharmId: Number){
    
    //promeni ocenu farmaceuta
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.http
    .put(this.endpoint.CHANGE_RATE + pharmId + '/PHARMACIST',this.ocena1,options)
    .pipe().subscribe(() => {if(confirm("Successfully changed rate.")) {
      this.router.navigate(["loggedUserHomePage"]);}}
    )
  }

}
