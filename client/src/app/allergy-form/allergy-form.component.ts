
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Endpoint } from '../util/endpoints-enum';

@Component({
  selector: 'app-allergy-form',
  templateUrl: './allergy-form.component.html',
  styleUrls: ['./allergy-form.component.css']
})
export class AllergyFormComponent implements OnInit {

  meds: any
  searchText
  endpoint = Endpoint;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http
      .get(this.endpoint.MED_LIST)
      .pipe(
        map(returnedMeds=> {
          this.meds = returnedMeds
        })
      ).subscribe()
  }

}
