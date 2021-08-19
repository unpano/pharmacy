import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Pharmacy } from '../dto/pharmacy';
import { Global } from '../util/global';

@Component({
  selector: 'app-pharmacy-profile',
  templateUrl: './pharmacy-profile.component.html',
  styleUrls: ['./pharmacy-profile.component.css']
})
export class PharmacyProfileComponent implements OnInit {

  pharmacy: Pharmacy = Global.clickedPharmacy
  userLogged = false

  constructor(private router: Router) { }

  ngOnInit(): void {
    if(Global.loggedUser.lastName != undefined){
      this.userLogged = true
    }
  }

  onScheduleDermatologist(){
    Global.clickedScheduleDA = true
    
    this.router.navigate(["dermAppointments"])
  }

}
