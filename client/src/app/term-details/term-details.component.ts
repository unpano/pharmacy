import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-term-details',
  templateUrl: './term-details.component.html',
  styleUrls: ['./term-details.component.css']
})
export class TermDetailsComponent implements OnInit {

  term:any
  pharmacist:any
  pharmacy:any
  endpoint = Endpoint

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.term = Global.clickedTerm

    //na osnovu id-ja terma naci koji je pharmacist
    const headers1 = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")}  
    let options1 = { headers: headers1 };

    this.http
    .get(this.endpoint.FIND_PHARMACIST + Global.clickedTerm.id,options1)
      .pipe(
        map(returnedUser => {
          console.log(returnedUser)
          this.pharmacist = returnedUser  

        })).subscribe()
  }

}
