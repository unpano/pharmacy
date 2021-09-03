import { GlobalPositionStrategy } from '@angular/cdk/overlay';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-order-meds',
  templateUrl: './order-meds.component.html',
  styleUrls: ['./order-meds.component.css']
})
export class OrderMedsComponent implements OnInit {


  meds : any
  endpoint = Endpoint


  medQ 

  constructor(public router: Router,public dialog: MatDialog,private http: HttpClient) {}

  ngOnInit(): void {


    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };
    
      this.http
      .get(this.endpoint.GET_ORDER_MEDS + Global.clickedOrder.id, options)
      .pipe(
        map(returnedPh=> {

          this.meds = returnedPh;
          ///nova funkcija koja vraca sve lekove na osnovu njihovih idjeva    ---- meds = tome



        })).subscribe()
        }

}
