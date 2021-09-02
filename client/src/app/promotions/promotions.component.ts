import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { AddPromotionComponent } from '../add-promotion/add-promotion.component';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-promotions',
  templateUrl: './promotions.component.html',
  styleUrls: ['./promotions.component.css']
})
export class PromotionsComponent implements OnInit {

  promotions: any

  endpoint = Endpoint;




  
  constructor(public router: Router,public dialog: MatDialog,private http: HttpClient) {

   }


   

  ngOnInit(): void {



    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.http
    .get(this.endpoint.GET_PROMOTIONS + Global.clickedPharmacy.id, options)
    .pipe(
      map(returnedProm=> {
        this.promotions = returnedProm
      
      })
    ).subscribe()



    
  }






  addPromotion()
  {
    let dialogRef = this.dialog.open(AddPromotionComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
})
    dialogRef.afterClosed().subscribe();
  }

  

}
