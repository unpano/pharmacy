import { Component, OnInit } from '@angular/core';
import { FindPostsService } from '../post/find-posts-service';
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

  login: Boolean = false
  signup: Boolean = Global.clickedSignUp

  constructor(public dialog: MatDialog) { }
  

  ngOnInit(): void {
    this.login = false
    this.signup = false
  }

  

  homeClicked(){
    this.login = false
  }

  onClickedLogin(){
    this.login = true
  }


}
