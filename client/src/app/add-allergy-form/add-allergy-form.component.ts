import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Med } from '../dto/med';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-add-allergy-form',
  templateUrl: './add-allergy-form.component.html',
  styleUrls: ['./add-allergy-form.component.css']
})
export class AddAllergyFormComponent implements OnInit {

  meds: any
  
  searchText
  endpoint = Endpoint;

  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    this.http
      .get(this.endpoint.MED_LIST)
      .pipe(
        map(returnedMeds=> {
          this.meds = returnedMeds
        })).subscribe()
  }

  addAllergy(med: Med){
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    const body=JSON.stringify(med);

    this.http
    .put(this.endpoint.USER_ADD_ALLERGY,body,options)
    .pipe().subscribe(() => {if(confirm("Successfully added allergy.")) {
      this.router.navigate(["profile"]);}}
    )

  }

}
