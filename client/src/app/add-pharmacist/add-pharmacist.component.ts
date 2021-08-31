import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { User } from '../dto/user';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-add-pharmacist',
  templateUrl: './add-pharmacist.component.html',
  styleUrls: ['./add-pharmacist.component.css']
})
export class AddPharmacistComponent implements OnInit {


  pharmacist : User;
  endpoint = Endpoint;

  constructor(public dialog: MatDialog,private http: HttpClient) { 

    this.pharmacist = new User;
  }




  ngOnInit(): void {
  }


  addPharmacist(){
    //kreiranje novog farmaceuta
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

this.pharmacist.pharmacy = Global.clickedPharmacy;
this.pharmacist.enabled = false;

      alert(this.pharmacist.pharmacy.id)

    this.http
      .post(this.endpoint.ADD_NEW_PHARMACIST, this.pharmacist, options)
      .pipe().subscribe(returnP => {}
     )
  }
}
