import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Constants } from '../../constants';
import { ModalDirective } from 'angular-bootstrap-md';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, AfterViewInit {

  @ViewChild('authenticationModal') authenticationModal: ModalDirective;

  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private auth: AuthenticationService, private router: Router){
    console.log('Constructor for Login Component');
  }

  ngAfterViewInit(): void {
    this.authenticationModal.show();
  }

  ngOnInit(): void {
    if(localStorage.getItem('user')) {
      this.router.navigate(['/users']);
    }
  }

  showModal() : void {
    if(localStorage.getItem('user')) {
      this.authenticationModal.show();
    }
  }

  logIn(): void {
    this.router.navigate(['/users']);
  }

  clearFormData(): void {
    this.errorMessage = "";
    this.password = "";
  }

  

}
