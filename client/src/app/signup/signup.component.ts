import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, map } from 'rxjs/operators';
import { User } from '../dto/user';
import { Endpoint } from '../util/endpoints-enum';

import { FormGroup, FormControl, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { EMPTY } from 'rxjs';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
    username: String;
    password: String;
    fullName: String;
    email: String;
    mobile: String;
    gen: number;
    gender: String;
    birthDate = new Date();
    website: String;
    biography: String;
    endpoint = Endpoint;
    login: Boolean = false
    error : string
    

  constructor(private router: Router,private http: HttpClient) { }

  ngOnInit(): void {
    this.login = false
    if(sessionStorage.getItem('token') != null){
      this.router.navigate(['loggedUserHomePage'])
    }
  }

  homeClicked(){
    this.login = false
  }

  onClickedLogin(){
    this.login = true
  }
  
  signUp(){
    let user: User = new User
    user.username = this.username
    user.password = this.password
 

    const headers = { 'content-type': 'application/json'}  // da bi odgovaralo json-u
    const body=JSON.stringify(user);   //konverzija objekta subscriber u json
    
    let options = { headers: headers };
    this.http.post<any>(this.endpoint.LOGIN+'signup/addUser/', body, options).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.error instanceof Error) {
          // A client-side or network error occurred. Handle it accordingly.
          alert("Bad request, please try again later.");
        } else {
          // The backend returned an unsuccessful response code.
          // The response body may contain clues as to what went wrong,
          alert("Username is not unique or you didn`t fill all of the fields. Please try again.");
        }

        // If you want to return a new response:
        //return of(new HttpResponse({body: [{name: "Default value..."}]}));

        // If you want to return the error on the upper level:
        //return throwError(error);

        // or just return nothing:
        return EMPTY;
      }),
      map(returnedPersonId => {
        //after sign up redirect to login
        if(confirm("Successfully created account.")) {
          this.router.navigate(["login"]);}

})
    ).subscribe()
  }
}
