import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Form } from '../dto/form';
import { IssuanceRegime } from '../dto/issuanceRegime';
import { Med } from '../dto/med';
import { User } from '../dto/user';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-edit-med',
  templateUrl: './edit-med.component.html',
  styleUrls: ['./edit-med.component.css']
})
export class EditMedComponent implements OnInit {

  med:Med = Global.clickedMed;
//obelezja koja menjamo
newName : String;
newType : String
newForm :Form;
newPorducer:String;
newIssuanceRegime : IssuanceRegime;
newNotes  : String;
newPrice : Number;
endpoint = Endpoint;

user: User = Global.loggedUser
onEditButton: Boolean = false

  constructor(public router: Router,public dialog: MatDialog,private http: HttpClient) { }

  ngOnInit(): void {
  }

  
  onEditMed(){
    this.onEditButton = true
  }

  
  onSubmit()
  {

    ///ovde menjamo med koji smo zapamtili kao global
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    if(this.newForm!= undefined)
      Global.clickedMed.form = this.newForm
    if(this.newIssuanceRegime != undefined)
      Global.clickedMed.issuanceRegime = this.newIssuanceRegime
    if(this.newName != undefined)
      Global.clickedMed.name = this.newName
    if(this.newNotes != undefined)
      Global.clickedMed.additionalNotes = this.newNotes
    if(this.newPorducer != undefined)
      Global.clickedMed.producer = this.newPorducer
    if(this.newType != undefined)
      Global.clickedMed.type = this.newType
      if(this.newPrice != undefined)
      Global.clickedMed.price = this.newPrice

    
    const body=JSON.stringify(Global.clickedMed);   //konverzija objekta subscriber u json
    

    this.http
    .put(this.endpoint.MED_UPDATE,body,options)
    .pipe().subscribe(() => {if(confirm("Successfully updated med.")) {
      
    }}
    )
  }



}
