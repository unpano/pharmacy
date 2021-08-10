import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { EMPTY } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { User } from '../dto/user';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';
import { VerifyProfileComponent } from '../verify-profile/verify-profile.component';

@Component({
  selector: 'app-nistagramer-profile',
  templateUrl: './nistagramer-profile.component.html',
  styleUrls: ['./nistagramer-profile.component.css']
})
export class NistagramerProfileComponent implements OnInit {

  user: User = Global.loggedUser
  onEditButton: Boolean

  newFullName: String
  newUsername: String;
  newPassword: String;
  newEmail: String;
  newMobile: String;
  newGender: String;
  newBirthDate = new Date();
  newWebsite: String;
  newBiography: String;
  newProfileType: String;
  newReceiveMessages: String;
  newEnableTags: String;

  endpoint = Endpoint;

  constructor(public dialog: MatDialog,private router: Router,private http: HttpClient) { }

  ngOnInit(): void {
    this.onEditButton = false
    
  }

  onEditProfile(){
    this.onEditButton = true
  }

  onVerifyProfile(){
    let dialogRef = this.dialog.open(VerifyProfileComponent)
    dialogRef.afterClosed().subscribe();
    
  }

  onSubmit(){
    let newUser: User = this.user

    if(this.newUsername != undefined){
      newUser.username = this.newUsername
    }
    if(this.newPassword != undefined){
      newUser.password = this.newPassword
    }
    if(this.newBiography != undefined){
      newUser.biography= this.newBiography
    }
    if(this.newBirthDate != undefined){
      newUser.birthDate = this.newBirthDate
    } 
    if(this.newEmail != undefined){
      newUser.email = this.newEmail
    }
    if(this.newFullName != undefined){
      newUser.fullName = this.newFullName
    }
    if(this.newMobile != undefined){
      newUser.mobile = this.newMobile
    }
    if(this.newGender == 'option1'){
      newUser.gender= 'male'
    }else if(this.newGender == 'option2'){
      newUser.gender= 'female'
    }
    if(this.newWebsite != undefined){
      newUser.website = this.newWebsite
    }
    if(this.newProfileType == 'option1'){
      newUser.isPublic= false
    }else if(this.newProfileType == 'option2'){
      newUser.isPublic = true
    }
    if(this.newReceiveMessages == 'option1'){
      newUser.receiveMessages= true
    }else if(this.newReceiveMessages == 'option2'){
      newUser.receiveMessages = false
    }
    if(this.newEnableTags == 'option1'){
      newUser.enableTags = true
    }else if(this.newEnableTags == 'option2'){
      newUser.enableTags = false
    }
    const headers = { 'content-type': 'application/json'}  // da bi odgovaralo json-u
    const body=JSON.stringify(newUser);   //konverzija objekta subscriber u json
    
    let options = { headers: headers };
    this.http.post<any>(this.endpoint.ACCOUNT+'signup/addUser/', body, options).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.error instanceof Error) {
          // A client-side or network error occurred. Handle it accordingly.
          alert("Bad request, please try again later.");
        } else {
          // The backend returned an unsuccessful response code.
          // The response body may contain clues as to what went wrong,
          alert("Invalid entry. Please try again.");
        }

        // If you want to return a new response:
        //return of(new HttpResponse({body: [{name: "Default value..."}]}));

        // If you want to return the error on the upper level:
        //return throwError(error);

        // or just return nothing:
        return EMPTY;
      }),

      )
    .subscribe()
  }

}
