import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
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

  user: any
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
    if(sessionStorage.getItem('token') == null){
      this.router.navigate([''])
    }
    const headers1 = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")}  
    let options1 = { headers: headers1 };

    this.http
    .get<Observable<User>>(this.endpoint.USER_PROFILE,options1)
      .pipe(
        map(returnedUser => {
          this.user = returnedUser  

        })).subscribe(() =>
        {
          const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")}  
    let options = { headers: headers };
    this.http
      .get(this.endpoint.USER_MED_LIST,options)
      .pipe(
        map(returnedAllergies=> {
          this.meds = returnedAllergies
        })
      ).subscribe()
        })
    //console.log(this.user)
    
  }

  onEditProfile(){
      this.onEditButton = true
  }

  onSubmit(){
    //poziv update metode za usera 
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")}  
    let options = { headers: headers };

    //nadjem ulogovanog prvo

    if(this.newFirstName != undefined)
      this.user.firstName = this.newFirstName
    if(this.newLastName != undefined)
      this.user.lastName = this.newLastName
    if(this.newUsername != undefined)
      this.user.username = this.newUsername
    if(this.newPhoneNumber != undefined)
      this.user.phoneNumber = this.newPhoneNumber
    if(this.newAddress != undefined)
      this.user.address = this.newAddress
    if(this.newCity != undefined)
      this.user.city = this.newCity
    if(this.newCountry != undefined)
      this.user.country = this.newCountry
    
    const body=JSON.stringify(this.user);   //konverzija objekta subscriber u json
    

    this.http
    .put(this.endpoint.USER_UPDATE,body,options)
    .pipe().subscribe(() => {if(confirm("Successfully updated profile.")) {
      this.router.navigate(["loggedUserHomePage"]);}}
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
