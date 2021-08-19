import { Component, OnInit } from '@angular/core';
import { Global } from '../util/global';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  userLogged: Boolean = false;

  constructor() { }

  ngOnInit(): void {
    
  }

}
