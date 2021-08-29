import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { Pharmacy } from '../dto/pharmacy';
import { Global } from '../util/global';
import { AdminPharmacyMedsComponent } from '../admin-pharmacy-meds/admin-pharmacy-meds.component';
import { AdminPharmacyPharmacistsComponent } from '../admin-pharmacy-pharmacists/admin-pharmacy-pharmacists.component';
import { Endpoint } from '../util/endpoints-enum';
import { map } from 'rxjs/operators';
import { AdminDermAppointmentsComponent } from '../admin-derm-appointments/admin-derm-appointments.component';
import { Promotion } from '../dto/promotion';
import { AddPromotionComponent } from '../add-promotion/add-promotion.component';

@Component({
  selector: 'app-pharmacy-details',
  templateUrl: './pharmacy-details.component.html',
  styleUrls: ['./pharmacy-details.component.css']
})
export class PharmacyDetailsComponent implements OnInit {

  meds : any;
  pharmacy: Pharmacy = Global.clickedPharmacy
  appointments : any;
  endpoint = Endpoint;
  showMap = false;

  promotion : Promotion;






  constructor(public router: Router,public dialog: MatDialog,private http: HttpClient) { }

  ngOnInit(): void {
  }

  viewMeds(pharmacy: Pharmacy){
    let dialogRef = this.dialog.open(AdminPharmacyMedsComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
})
    dialogRef.afterClosed().subscribe(res => Global.reserveFromPickedPharmacy = false);
  }


  viewPharmacists(pharmacy: Pharmacy){
    let dialogRef = this.dialog.open(AdminPharmacyPharmacistsComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
})
    dialogRef.afterClosed().subscribe(res => Global.reserveFromPickedPharmacy = false);
  }


  viewTerms(pharmacy: Pharmacy){
      let dialogRef = this.dialog.open(AdminDermAppointmentsComponent,{
        autoFocus: false,
        maxHeight: '90vh' //you can adjust the value as per your view
  })
      dialogRef.afterClosed().subscribe();
      
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
