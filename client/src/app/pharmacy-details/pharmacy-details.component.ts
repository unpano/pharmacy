import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DermAppointmentListComponent } from '../derm-appointment-list/derm-appointment-list.component';
import { Pharmacy } from '../dto/pharmacy';
import { Global } from '../util/global';

@Component({
  selector: 'app-pharmacy-details',
  templateUrl: './pharmacy-details.component.html',
  styleUrls: ['./pharmacy-details.component.css']
})
export class PharmacyDetailsComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void { }

  pharmacy: Pharmacy = Global.clickedPharmacy
  


}
