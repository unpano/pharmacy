import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { AddOrderComponent } from '../add-order/add-order.component';
import { MedQuantityes } from '../dto/medQuantities';
import { Order } from '../dto/order';
import { OfferListComponent } from '../offer-list/offer-list.component';
import { OrderMedsComponent } from '../order-meds/order-meds.component';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {



  orders: any

  endpoint = Endpoint;

  meds : any




  
  constructor(public router: Router,public dialog: MatDialog,private http: HttpClient) {

   }


   

  ngOnInit(): void {



    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.http
    .get(this.endpoint.GET_ORDERS + Global.clickedPharmacy.id, options)
    .pipe(
      map(returnedProm=> {
        this.orders = returnedProm


      
      })
    ).subscribe()



    
  }



getMeds(o : Order)
{


  Global.clickedOrder = o;

  let dialogRef = this.dialog.open(OrderMedsComponent,{
    autoFocus: false,
    maxHeight: '90vh' //you can adjust the value as per your view
})
  dialogRef.afterClosed().subscribe();
}


getOffers(o : Order)
{

  Global.clickedOrder = o;

  let dialogRef = this.dialog.open(OfferListComponent,{
    autoFocus: false,
    maxHeight: '90vh' //you can adjust the value as per your view
    })
  dialogRef.afterClosed().subscribe();
}


  addOrder()
  {
    let dialogRef = this.dialog.open(AddOrderComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
})
    dialogRef.afterClosed().subscribe();
  }

  


}
