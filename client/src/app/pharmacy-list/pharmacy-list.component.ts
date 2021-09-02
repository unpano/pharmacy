import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Sort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Pharmacy } from '../dto/pharmacy';
import { PharmacyMedsComponent } from '../pharmacy-meds/pharmacy-meds.component';
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
  sortedData: any
  searchText
  endpoint = Endpoint;

  constructor(public dialog: MatDialog,private http: HttpClient,private router: Router) {}

  ngOnInit(): void {
    if(sessionStorage.getItem('token') == null){
      this.router.navigate([''])
    }else
     this.router.navigate(['loggedUserHomePage'])
    //Da li cu prikazati sve apoteke ili one u kojima ima odredjeni lek
    if(Global.clickedMed.id != undefined){
      this.http
        .get(this.endpoint.MED_PHARMACY_LIST + Global.clickedMed.id)
          .pipe(
            map(returnedPharmacies=> {
              this.pharmacies = returnedPharmacies
              this.sortedData = this.pharmacies.slice()
            })).subscribe( Global.clickedMed.id = undefined)
    }else{
      this.http
        .get(this.endpoint.PHARMACY_LIST)
          .pipe(
            map(returnedPharmacies=> {
              this.pharmacies = returnedPharmacies
              this.sortedData = this.pharmacies.slice()
            })).subscribe()
    }
    
  }

  clickedPharmacy(pharmacy: Pharmacy){
    Global.clickedPharmacy = pharmacy
    let dialogRef = this.dialog.open(PharmacyProfileComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
    })
    dialogRef.afterClosed().subscribe();
  }

  viewMeds(pharmacy: Pharmacy){
    Global.clickedPharmacy = pharmacy
    Global.reserveFromPickedPharmacy = true
    let dialogRef = this.dialog.open(PharmacyMedsComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
    })
    dialogRef.afterClosed().subscribe(res => Global.reserveFromPickedPharmacy = false);
  }

  sortData(sort: Sort) {
    const data = this.pharmacies.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return compare(a.name, b.name, isAsc);
        case 'address': return compare(a.address, b.address, isAsc);
        case 'city': return compare(a.city, b.city, isAsc);
        case 'rate': return compare(a.rate, b.rate, isAsc);
        default: return 0;
      }
    });
  }
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
