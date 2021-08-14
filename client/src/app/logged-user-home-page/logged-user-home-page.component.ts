import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { map } from 'rxjs/operators';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-logged-user-home-page',
  templateUrl: './logged-user-home-page.component.html',
  styleUrls: ['./logged-user-home-page.component.css']
})
export class LoggedUserHomePageComponent implements OnInit {

  profile: Boolean = false

  endpoint = Endpoint;
  
  constructor(public dialog: MatDialog,private http: HttpClient) { }

  ngOnInit(): void {
  }

  clickedProfile(){
    this.profile = true
    
  }

  homeClicked(){
    //SVE STO SE NE PRIKAZUJE POSTAVITI NA FALSE
    this.profile = false
  }
 

}
