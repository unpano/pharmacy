import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EMPTY } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Verification } from '../dto/verification';
import { UserDetailsComponent } from '../user-details/user-details.component';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-verification-list',
  templateUrl: './verification-list.component.html',
  styleUrls: ['./verification-list.component.css']
})
export class VerificationListComponent implements OnInit {

  requests: Verification[] = []
  endpoint = Endpoint;

  constructor(public dialog: MatDialog,private http: HttpClient) { }

  ngOnInit(): any {
    return this.http
      .get(this.endpoint.ACCOUNT+'requests' )
      .pipe(
        map(returnedArrayOfRequests => {
         
          let rt: any
            rt =  returnedArrayOfRequests
            return rt
        })
      ).subscribe(res => this.requests = res)
  }

  onViewProfile(userId: Number){
    console.log(userId)
    //pronadji podatke o user-u
    this.http
      .get(this.endpoint.ACCOUNT+'findUser/' + userId)
      .pipe(
        map(returnedUser => {
         
          let rt: any
            rt =  returnedUser
            return rt
        })
      ).subscribe(res => {Global.viewUserDetails = res, Global.mappedUser=true})
    //otvori modalni dijalog i prikazi podatke o user-u
    if( Global.mappedUser == true){
      let dialogRef = this.dialog.open(UserDetailsComponent)
      dialogRef.afterClosed().subscribe();
    }
    
  }

  onDeleteRequest(id: Number){
       const headers = { 'content-type': 'application/json'}  // da bi odgovaralo json-u
       let options = { headers: headers };
    this.http.delete(this.endpoint.ACCOUNT+'deleteRequest/' + id,options)
        .subscribe();
  }

  onVerify(id: Number){
    const headers = { 'content-type': 'application/json'}  // da bi odgovaralo json-u
       let options = { headers: headers };
    this.http.delete(this.endpoint.ACCOUNT+'deleteRequest/' + id,options)
        .subscribe(res => {
          Global.viewUserDetails.verified = true
          const headers = { 'content-type': 'application/json'}  // da bi odgovaralo json-u
          const body=JSON.stringify(Global.viewUserDetails);   //konverzija objekta subscriber u json
          
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
            map(returnedPersonId => {
              alert("Profile verified successfully!");
      
      })
          ).subscribe(res => this.ngOnInit())

        });
    
  }

}
