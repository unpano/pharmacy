import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Sort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-prescription-list',
  templateUrl: './prescription-list.component.html',
  styleUrls: ['./prescription-list.component.css']
})
export class PrescriptionListComponent implements OnInit {

  prescriptions: any
  sortedData: any
  searchText
  endpoint = Endpoint;

  constructor(public router: Router,public dialog: MatDialog,private http: HttpClient) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('token') == null){
      this.router.navigate([''])
    }
    //dobavljam listu recepata koji su prepisani pacijentu
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")}  
    let options = { headers: headers };
    
    this.http
      .get(this.endpoint.USER_PRESCRIPTIONS,options)
      .pipe(
        map(returnedPrescriptions=> {
          this.prescriptions = returnedPrescriptions
          this.sortedData = this.prescriptions.slice()
        })
      ).subscribe()
  }

  sortData(sort: Sort) {
    const data = this.prescriptions.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'date': return compare(a.date, b.date, isAsc);
        case 'status': return compare(a.status, b.status, isAsc);
        case 'med': return compare(a.med.name, b.med.name, isAsc);
        default: return 0;
      }
    });
  }
 

}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}

