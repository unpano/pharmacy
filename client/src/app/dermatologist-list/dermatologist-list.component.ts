import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { User } from '../dto/user';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-dermatologist-list',
  templateUrl: './dermatologist-list.component.html',
  styleUrls: ['./dermatologist-list.component.css']
})
export class DermatologistListComponent implements OnInit {

  dermatologists: any
  ratedDermatologists: any
  ocena: Number
  ocena1: Number
  searchText
  searchText1
  endpoint = Endpoint

  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    //dobavi listu dermatologa
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };
    
    this.http
      .get(this.endpoint.DERMATOLOGIST_LIST,options)
      .pipe(
        map(returnedDermatologists=> {
          this.dermatologists = returnedDermatologists
        })
      ).subscribe(() => {
              //nadjem vec ocenjene dermatologe
                this.http
              .get(this.endpoint.DERMATOLOGIST_LIST + '/rated' ,options)
              .pipe(
                map(returnedDermatologists=> {
                  this.ratedDermatologists = returnedDermatologists
                })
              ).subscribe()
      })
  }

  rateDermatologist(dermatologistId: Number){
    //oceni dermatologa
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.http
    .put(this.endpoint.RATE_DERMATOLOGIST + dermatologistId,this.ocena,options)
    .pipe().subscribe(() => {if(confirm("Successfully rated dermatologist.")) {
      this.router.navigate(["loggedUserHomePage"]);}}
    )
  }

  changeRate(dermId: Number){
    
    //promeni ocenu dermatologa
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.http
    .put(this.endpoint.CHANGE_RATE + dermId + '/DERMATOLOGIST',this.ocena1,options)
    .pipe().subscribe(() => {if(confirm("Successfully changed rate.")) {
      this.router.navigate(["loggedUserHomePage"]);}}
    )
  }
}
