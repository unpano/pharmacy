import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Endpoint } from '../util/endpoints-enum';

@Component({
  selector: 'app-pharmacy-dermatologists',
  templateUrl: './pharmacy-dermatologists.component.html',
  styleUrls: ['./pharmacy-dermatologists.component.css']
})
export class PharmacyDermatologistsComponent implements OnInit {

  dermatologists: any
  endpoint = Endpoint;


  constructor(public router: Router,public dialog: MatDialog,private http: HttpClient) {}

  ngOnInit(): void {



    
  }










}
