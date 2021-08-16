import { Component, OnInit } from '@angular/core';
import { Pharmacy } from '../dto/pharmacy';
import { Global } from '../util/global';

@Component({
  selector: 'app-pharmacy-profile',
  templateUrl: './pharmacy-profile.component.html',
  styleUrls: ['./pharmacy-profile.component.css']
})
export class PharmacyProfileComponent implements OnInit {

  pharmacy: Pharmacy = Global.clickedPharmacy

  constructor() { }

  ngOnInit(): void {
  }

  onScheduleDermatologist(){

  }

}
