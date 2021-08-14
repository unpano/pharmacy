import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { map } from 'rxjs/operators';
import { Pharmacy } from '../dto/pharmacy';
import { PharmacyProfileComponent } from '../pharmacy-profile/pharmacy-profile.component';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-pharmacy-list',
  templateUrl: './pharmacy-list.component.html',
  styleUrls: ['./pharmacy-list.component.css']
})
export class PharmacyListComponent implements OnInit {

  pharmacies: any
  searchText
  endpoint = Endpoint;

  constructor(public dialog: MatDialog,private http: HttpClient) { }

  ngOnInit(): void {
    this.http
      .get(this.endpoint.PHARMACY_LIST)
      .pipe(
        map(returnedPharmacies=> {
          this.pharmacies = returnedPharmacies
        })
      ).subscribe()
  }

  clickedPharmacy(pharmacy: Pharmacy){
    Global.clickedPharmacy = pharmacy
    let dialogRef = this.dialog.open(PharmacyProfileComponent)
    dialogRef.afterClosed().subscribe();
  }

}
