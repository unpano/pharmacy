import { Component, OnInit } from '@angular/core';
import { Global } from '../util/global';

@Component({
  selector: 'app-meds',
  templateUrl: './meds.component.html',
  styleUrls: ['./meds.component.css']
})
export class MedsComponent implements OnInit {

  userLogged: Boolean = false

  constructor() { }

  ngOnInit(): void {
    Global.allMeds = true
    if(sessionStorage.getItem('token') != null){
      this.userLogged = true
    }
    else
      this.userLogged = false
  }

}
