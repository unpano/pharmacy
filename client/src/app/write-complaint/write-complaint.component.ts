import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-write-complaint',
  templateUrl: './write-complaint.component.html',
  styleUrls: ['./write-complaint.component.css']
})
export class WriteComplaintComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('token') == null){
      this.router.navigate([''])
    }
  }

}
