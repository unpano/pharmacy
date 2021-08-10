import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { NistagramerProfileComponent } from '../nistagramer-profile/nistagramer-profile.component';
import { Global } from '../util/global';

@Component({
  selector: 'app-nistagramer-home-page',
  templateUrl: './nistagramer-home-page.component.html',
  styleUrls: ['./nistagramer-home-page.component.css']
})
export class NistagramerHomePageComponent implements OnInit {

  profile: Boolean = false
  myPosts: Boolean = false
  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  clickedProfile(){
    this.profile = true
  }

  homeClicked(){
    //SVE STO SE NE PRIKAZUJE POSTAVITI NA FALSE
    this.profile = false
    this.myPosts = false
  }

  myPostsClicked(){
    this.myPosts = true
  }

}
