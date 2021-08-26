import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-pharmacy-rate-list',
  templateUrl: './pharmacy-rate-list.component.html',
  styleUrls: ['./pharmacy-rate-list.component.css']
})
export class PharmacyRateListComponent implements OnInit {

  pharmacies: any
  ratedPharmacies: any
  ocena: Number
  ocena1: Number
  searchText
  searchText1
  endpoint = Endpoint

  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    //dobavi listu apoteka
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };
    
    this.http
      .get(this.endpoint.PHARMACY_RATE_LIST,options)
      .pipe(
        map(returnedPharmacies => {
          this.pharmacies = returnedPharmacies
        })
      ).subscribe(()=>
      {
        //lista vec ocenjenih apoteka
          this.http
        .get(this.endpoint.PHARMACY_RATE_LIST + '/rated',options)
        .pipe(
          map(returnedPharmacies => {
            this.ratedPharmacies = returnedPharmacies
          })
        ).subscribe()
      })
  }

  ratePharmacy(pharmacyId: Number){
     //oceni apoteku
     const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.http
    .put(this.endpoint.RATE_PHARMACY + pharmacyId,this.ocena,options)
    .pipe().subscribe(() => {if(confirm("Successfully rated pharmacy.")) {
      this.router.navigate(["loggedUserHomePage"]);}}
    )
  }

  changeRate(pharmacyId: Number){
    
    //promeni ocenu apoteke
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.http
    .put(this.endpoint.CHANGE_RATE + pharmacyId + '/PHARMACY',this.ocena1,options)
    .pipe().subscribe(() => {if(confirm("Successfully changed rate.")) {
      this.router.navigate(["loggedUserHomePage"]);}}
    )
  }

}
