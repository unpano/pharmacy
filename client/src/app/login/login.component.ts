import { DatePipe, SlicePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EMPTY } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Credentials } from '../dto/credentials';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  
  username: string;
  password: string;
  credentials: Credentials = new Credentials();
  endpoint = Endpoint;

  constructor(private router: Router,private http: HttpClient) { }

  ngOnInit(): void {
  }

  onClickedSignUp(){
    Global.clickedSignUp = true;
  }

  login() {
    const headers = { 'content-type': 'application/json'}  
    let options = { headers: headers };

    this.credentials.username = this.username
    this.credentials.password = this.password

    const body=JSON.stringify(this.credentials);  
    
    this.http.post<any>(this.endpoint.LOGIN, body, options).pipe(
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
      map(returnedToken => {
          
          Global.token.access_token = returnedToken["access_token"]
          Global.token.expires_in = returnedToken["expires_in"]
          
          this.router.navigate(["/loggedUser"]);

})
    ).subscribe(res =>{
      //console.log(Global.token.access_token)
      const headers = { 
        'content-type': 'application/json',
        'Authorization': 'Bearer ' + Global.token.access_token}  
      let options = { headers: headers };
      this.http
      .get(this.endpoint.USER_PROFILE,options)
      .pipe(
        map(returnedUser => {
          let user: any
          user = returnedUser
          Global.loggedUser = user
        })
      ).subscribe()
      })
  
  }


}


