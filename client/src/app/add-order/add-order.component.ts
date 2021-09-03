import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AddMedOrderComponent } from '../add-med-order/add-med-order.component';
import { Offer } from '../dto/offer';
import { Order } from '../dto/order';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-add-order',
  templateUrl: './add-order.component.html',
  styleUrls: ['./add-order.component.css']
})
export class AddOrderComponent implements OnInit {

  
  order : Order;
  endpoint = Endpoint;
  date

  o : any




  constructor(private http: HttpClient,private router: Router, public dialog: MatDialog) { 
    this.order = new Order();

  }



  
  ngOnInit(): void {




  }

  /// ovde cemo da kreiramo objekat  medsQuantity
  addMed()
  {

    let dialogRef = this.dialog.open(AddMedOrderComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
})
    dialogRef.afterClosed().subscribe();

    
  }


  

  addOrder()
  {



    //kreiranje nove narudzbine 
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    this.order.pharmacy = Global.clickedPharmacy;
    this.order.date = this.date;

    this.http
      .post(this.endpoint.ADD_ORDER, this.order, options)
      .pipe().subscribe(returnP => 
      {


          alert("You created new order!")
            this.o = returnP;
        

      })
        Global.order = this.o;

    }
}
