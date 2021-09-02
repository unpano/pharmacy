import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { map } from 'rxjs/operators';
import { Endpoint } from '../util/endpoints-enum';

import { Sort } from '@angular/material/sort';
import { Global } from '../util/global';
import { Router } from '@angular/router';

@Component({
  selector: 'app-promotions',
  templateUrl: './promotions.component.html',
  styleUrls: ['./promotions.component.css']
})
export class PromotionsComponent implements OnInit {

  pharmacies: any
  sortedData: any
  searchText
  endpoint = Endpoint;

  constructor(public dialog: MatDialog,private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('token') == null){
      this.router.navigate([''])
    }

    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")}  
    let options = { headers: headers };

    this.http
        .get(this.endpoint.PHARMACY_LIST + 'promoted',options)
          .pipe(
            map(returnedPharmacies=> {
              this.pharmacies = returnedPharmacies
              this.sortedData = this.pharmacies.slice()
            })).subscribe()
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

