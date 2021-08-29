import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Form } from '../dto/form';
import { IssuanceRegime } from '../dto/issuanceRegime';
import { Med } from '../dto/med';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-add-med',
  templateUrl: './add-med.component.html',
  styleUrls: ['./add-med.component.css']
})
export class AddMedComponent implements OnInit {

  meds: any
  searchText
  endpoint = Endpoint;
  loggedUser: boolean = false




  

  constructor(public router: Router,public dialog: MatDialog,private http: HttpClient) {

   }

  ngOnInit(): void {


      this.http
      .get(this.endpoint.MED_LIST)
      .pipe(
        map(returnedMeds=> {
          this.meds = returnedMeds
          console.log(returnedMeds)
        })
      ).subscribe()
    
  }

  add(med : Med)
  {
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };



    this.http
    .put(this.endpoint.ADD_MED + med.id,Global.clickedPharmacy, options)
    .pipe(
      map(returnedPh=> {
  
          alert("The medicine added seccessfully!")
  
      })).subscribe()
    }


  

}
