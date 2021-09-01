import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { User } from '../dto/user';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-add-dermatologist',
  templateUrl: './add-dermatologist.component.html',
  styleUrls: ['./add-dermatologist.component.css']
})
export class AddDermatologistComponent implements OnInit {

  
  dermatologist : User;
  endpoint = Endpoint;

  constructor(public dialog: MatDialog,private http: HttpClient) { 

    this.dermatologist = new User;
  }




  ngOnInit(): void {
  }


  addDermatologist(){
    //kreiranje novog farmaceuta
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

this.dermatologist.pharmacy = Global.clickedPharmacy;
this.dermatologist.enabled = false;
this.dermatologist.stars = 1;

    this.http
      .post(this.endpoint.ADD_NEW_DERMATOLOGIST, this.dermatologist, options)
      .pipe().subscribe(returnP => {}
     )
  }



  
}
