import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { DermAppointmentListComponent } from '../derm-appointment-list/derm-appointment-list.component';
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

  constructor(public dialog: MatDialog,private router: Router) { }

  ngOnInit(): void {
    if(Global.loggedUser.lastName != undefined){
      this.userLogged = true
    }
  }

  onSchedule(){
    let dialogRef = this.dialog.open(DermAppointmentListComponent,{
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
})
    dialogRef.afterClosed().subscribe();
    
  }

}
