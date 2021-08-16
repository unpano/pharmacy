import { Component, OnInit } from '@angular/core';
import { Post } from '../dto/post';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { Global } from '../util/global';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  login: Boolean = Global.clickedLogin
  signup: Boolean = Global.clickedSignUp
  meds: Boolean = Global.clickedMeds

  constructor(public dialog: MatDialog) { }
  

  ngOnInit(): void {
    this.login = false
    this.signup = false
    this.meds = false
  }

  homeClicked(){
    this.login = false
    this.meds = false
    Global.clickedMeds = false
  }

  onClickedLogin(){
    this.login = true
    this.meds = false
  }

  onClickedMeds(){
    Global.clickedMeds = true
    this.meds = true
    this.login = false
    Global.clickedLogin = false
    
  }


}
