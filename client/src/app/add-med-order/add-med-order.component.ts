import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Med } from '../dto/med';
import { MedQuantityes } from '../dto/medQuantities';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-add-med-order',
  templateUrl: './add-med-order.component.html',
  styleUrls: ['./add-med-order.component.css']
})
export class AddMedOrderComponent implements OnInit {

  meds: any

  endpoint = Endpoint;
  loggedUser: boolean = false

  medQuantity : MedQuantityes;



  

  constructor(public router: Router,public dialog: MatDialog,private http: HttpClient) {

   }

   ngOnInit(): void {

    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };


    this.http
    .get(this.endpoint.MED_LIST)
    .pipe(
      map(returnedMeds=> {
        this.meds = returnedMeds
      })
    ).subscribe()



  }

  onSelectedMed(med : Med)
  {
      this.medQuantity.med = med;
  }





  add()
  {

  




    //kreiranje nove narudzbine 
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

  this.medQuantity.order = Global.order

  const body=JSON.stringify(this.medQuantity);


        this.http
          .post("http://localhost:8084/quantity/addMedQuantity/", body, options)
          .pipe().subscribe(returnP => 
          {

            alert(this.medQuantity.quantity)

              alert("You added med to order!")
            

          })

}


  

}
