import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { map } from 'rxjs/operators';
import { Med } from '../dto/med';
import { PharmacyListComponent } from '../pharmacy-list/pharmacy-list.component';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-med-list',
  templateUrl: './med-list.component.html',
  styleUrls: ['./med-list.component.css']
})
export class MedListComponent implements OnInit {

  meds: any
  clickedMeds : Boolean = Global.clickedMeds
  searchText
  endpoint = Endpoint;

  constructor(public dialog: MatDialog,private http: HttpClient) { }

  ngOnInit(): void {
    if(Global.clickedMeds == true){
      this.http
      .get(this.endpoint.MED_LIST)
      .pipe(
        map(returnedMeds=> {
          this.meds = returnedMeds
        })
      ).subscribe()
    }else{
      this.http
      .get(this.endpoint.PHARMACY_MED_LIST + Global.clickedPharmacy.id)
      .pipe(
        map(returnedMeds=> {
          this.meds = returnedMeds
        })
      ).subscribe()
    }
    
  }

  viewPharmacies(med: Med){
    Global.clickedMed = med
    let dialogRef = this.dialog.open(PharmacyListComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
})
    dialogRef.afterClosed().subscribe();
  }

}
