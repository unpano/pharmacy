import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Offer } from '../dto/offer';
import { Endpoint } from '../util/endpoints-enum';

@Component({
  selector: 'app-add-order',
  templateUrl: './add-order.component.html',
  styleUrls: ['./add-order.component.css']
})
export class AddOrderComponent implements OnInit {

  
  offer : Offer;
  endpoint = Endpoint;





  constructor(private http: HttpClient,  private router: Router) { 
    this.offer = new Offer();

  }



  
  ngOnInit(): void {




  }




  

  addPromotion()
  {
    
  }
}
