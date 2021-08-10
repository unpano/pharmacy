import { DatePipe, SlicePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EMPTY } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
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
  endpoint = Endpoint;

  constructor(private router: Router,private http: HttpClient) { }

  ngOnInit(): void {
  }

  onClickedSignUp(){
    Global.clickedSignUp = true;
  }

  login() {
    this.http
      .get(this.endpoint.ACCOUNT+'login/' + this.username + "/" + this.password)
      .pipe(
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
        map(returnedUser => {
          if(returnedUser==null)
            alert("Invalid credentials. Please try again.")
          Global.loggedUser.id = returnedUser["id"]
          Global.loggedUser.username = returnedUser["username"]
          Global.loggedUser.password = returnedUser["password"]
          Global.loggedUser.biography = returnedUser["biography"] 
          
          var datePipe = new DatePipe('en-US'); 
          let newDate = new Date(datePipe.transform(returnedUser["birthDate"], 'dd-MM-yyyy'));
          Global.loggedUser.birthDate = newDate
          Global.loggedUser.email = returnedUser["email"]
          Global.loggedUser.fullName = returnedUser["fullName"]
          Global.loggedUser.mobile = returnedUser["mobile"]
          Global.loggedUser.gender = returnedUser["gender"]
          Global.loggedUser.website = returnedUser["website"]
          Global.loggedUser.role = returnedUser["role"]
          Global.loggedUser.isPublic = returnedUser["isPublic"]
          Global.loggedUser.receiveMessages = returnedUser["receiveMessages"]
          Global.loggedUser.enableTags = returnedUser["enableTags"]
          Global.loggedUser.verified = returnedUser["verified"]
            if(returnedUser != undefined)
                if(returnedUser["role"]=="admin"){
                  this.router.navigate(["loggedAdmin"]);
                }else if(returnedUser["role"]=="agent"){
                  this.router.navigate(["loggedAgent"]);
                }else{
                  this.router.navigate(["loggedNistagramer"]);
                }
              
              else
                alert("Invalid credentials")
        })
      ).subscribe()
      
  }

 

}


