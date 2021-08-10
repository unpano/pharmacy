import { CloseScrollStrategyConfig } from '@angular/cdk/overlay/scroll/close-scroll-strategy';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EMPTY } from 'rxjs';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';
import { catchError, map } from 'rxjs/operators';
import { Verification } from '../dto/verification';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-verify-profile',
  templateUrl: './verify-profile.component.html',
  styleUrls: ['./verify-profile.component.css']
})
export class VerifyProfileComponent implements OnInit {

  category: String; 
  public formData = new FormData();
  
  req: Verification = new Verification()
  ReqJson: any = {};
  request: Verification = new Verification;
  
  endpoint = Endpoint;
  
  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
  }

  onSendRequest(){
    this.req.category = this.category
    this.req.userId = Global.loggedUser.id
    this.req.image = ' '
    this.req.fullName = Global.loggedUser.fullName
    const headers = { 'content-type': 'application/json'}  // da bi odgovaralo json-u
    const body=JSON.stringify(this.req);   //konverzija objekta subscriber u json
    
    let options = { headers: headers };
    this.http.post<any>(this.endpoint.ACCOUNT+'verify', body, options).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.error instanceof Error) {
          // A client-side or network error occurred. Handle it accordingly.
          alert("Bad request, please try again later.");
        } else {
          // The backend returned an unsuccessful response code.
          // The response body may contain clues as to what went wrong,
          alert("You already sent request for verification. Wait until your profile is verified.");
        }

        // If you want to return a new response:
        //return of(new HttpResponse({body: [{name: "Default value..."}]}));

        // If you want to return the error on the upper level:
        //return throwError(error);

        // or just return nothing:
        return EMPTY;
      }),
      map(returnedRequest => {
        this.request = returnedRequest
        alert("Successfuly sent verification request.")
        this.router.navigate(["loggedNistagramer"]);

})
    ).subscribe(res=>{
      //posaljem novi zahtev koji ce da namapira file na zahtev
      const headerss = new HttpHeaders({})  // da bi odgovaralo json-u
      
      //const bodyy=JSON.stringify(this.formData);   //konverzija objekta subscriber u json
      
      let optionss = { headers: headerss };
      this.http.post<any>(this.endpoint.ACCOUNT+'addImage/' + this.request.id, this.formData, optionss).pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.error instanceof Error) {
            // A client-side or network error occurred. Handle it accordingly.
            alert("Bad request, please try again later.");
          } else {
            // The backend returned an unsuccessful response code.
            // The response body may contain clues as to what went wrong,
            alert("You already sent request for verification. Wait until your profile is verified.");
          }
  
          // If you want to return a new response:
          //return of(new HttpResponse({body: [{name: "Default value..."}]}));
  
          // If you want to return the error on the upper level:
          //return throwError(error);
  
          // or just return nothing:
          return EMPTY;
        }),
        map(returnedPersonId => {
          alert("Successfuly sent verification request.")
          this.router.navigate(["loggedNistagramer"]);
  
  })
      ).subscribe(
        //posaljem novi zahtev koji ce da namapira file na zahtev
  
      )}
    )

  }

  uploadFiles( file ) {
    //console.log( 'file', file )
    for ( let i = 0; i < file.length; i++ ) {
        this.formData.append( "file", file[i], file[i]['name'] );
    }
}

}
