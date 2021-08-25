import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { AddAllergyFormComponent } from '../add-allergy-form/add-allergy-form.component';
import { User } from '../dto/user';
import { PickWhomToRateComponent } from '../pick-whom-to-rate/pick-whom-to-rate.component';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: User = Global.loggedUser
  meds: any
  onEditButton: Boolean = false
  dataArray = []


  newFirstName: String;
  newLastName: String;
  newUsername: String;
  //newPassword: String;
  newPhoneNumber: String;
  newAddress: String;
  newCity: String;
  newCountry: String;

  endpoint = Endpoint;

  constructor(public dialog: MatDialog,private router: Router,private http: HttpClient) { }

  ngOnInit(): void {
    //console.log(this.user)
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };
    this.http
      .get(this.endpoint.USER_MED_LIST + Global.loggedUser.id,options)
      .pipe(
        map(returnedAllergies=> {
          this.meds = returnedAllergies
        })
      ).subscribe()
  }

  onEditProfile(){
      this.onEditButton = true
  }

  onSubmit(){
    //poziv update metode za usera 
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    if(this.newFirstName != undefined)
      Global.loggedUser.firstName = this.newFirstName
    if(this.newLastName != undefined)
      Global.loggedUser.lastName = this.newLastName
    if(this.newUsername != undefined)
      Global.loggedUser.username = this.newUsername
    if(this.newPhoneNumber != undefined)
      Global.loggedUser.phoneNumber = this.newPhoneNumber
    if(this.newAddress != undefined)
      Global.loggedUser.address = this.newAddress
    if(this.newCity != undefined)
      Global.loggedUser.city = this.newCity
    if(this.newCountry != undefined)
      Global.loggedUser.country = this.newCountry
    
    const body=JSON.stringify(Global.loggedUser);   //konverzija objekta subscriber u json
    

    this.http
    .put(this.endpoint.USER_UPDATE,body,options)
    .pipe().subscribe(() => {if(confirm("Successfully updated profile.")) {
      this.router.navigate(["loggedUser"]);}}
    )
  }

  onAddAllergy(){
    let dialogRef = this.dialog.open(AddAllergyFormComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
    })
    dialogRef.afterClosed().subscribe();
  }

  onAddComplaint(){
    this.router.navigate(["writeComplaint"]);
  }

  onRate(){
    let dialogRef = this.dialog.open(PickWhomToRateComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
    })
    dialogRef.afterClosed().subscribe();
  }
 
}
