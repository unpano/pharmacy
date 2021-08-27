import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Med } from '../dto/med';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-add-allergy-form',
  templateUrl: './add-allergy-form.component.html',
  styleUrls: ['./add-allergy-form.component.css']
})
export class AddAllergyFormComponent implements OnInit {

  meds: any
  sortedData: any
  searchText
  endpoint = Endpoint;

  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    this.http
      .get(this.endpoint.MED_LIST)
      .pipe(
        map(returnedMeds=> {
          this.meds = returnedMeds
          this.sortedData = this.meds.slice()
        })).subscribe()
  }

  addAllergy(med: Med){
    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

    const body=JSON.stringify(med);

    this.http
    .put(this.endpoint.USER_ADD_ALLERGY,body,options)
    .pipe().subscribe(() => {if(confirm("Successfully added allergy.")) {
      this.router.navigate(["profile"]);}}
    )

  }
  sortData(sort: Sort) {
    const data = this.meds.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return compare(a.name, b.name, isAsc);
        case 'type': return compare(a.type, b.type, isAsc);
        case 'form': return compare(a.form, b.form, isAsc);
        case 'allergy': return compare(a.allergy, b.allergy, isAsc);
        default: return 0;
      }
    });
  }
 

}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}

