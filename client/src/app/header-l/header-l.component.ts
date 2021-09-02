import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-header-l',
  templateUrl: './header-l.component.html',
  styleUrls: ['./header-l.component.css']
})
export class HeaderLComponent implements OnInit {

  endpoint = Endpoint

  constructor(private router: Router,private http: HttpClient) { }

  ngOnInit(): void {
    
    
  }
  logOut(){
    sessionStorage.clear()
    this.router.navigate([''])
  }

}
