import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { ModalDirective } from 'angular-bootstrap-md';
import { Constants } from 'src/app/constants';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  // common elements
  mainMessage: string = '';
  filterInPlace: boolean = false;

  // find all users
  usersArray: any = [];
  usersTableHeaders: any = ['id', 'address', 'city', 'country', 'email', 'firstName', 'lastName', 'password', 'phoneNumber'];
  

  ///////////////////////////////////////////////
  constructor(private userService: UserService) { }

  // initial component being loaded
  ngOnInit(): void {
    this.findAllUsers();
  }

  //////////////// API business logic //////////////
  findAllUsers(): void {
    // clear the array
    this.usersArray = [];

    // call findAllUsers service
    this.userService.findAllUsers()
      .subscribe(data => {
        for (let user of data.content) {
          this.usersArray.push(user);
          console.log(user);
        }
      },
      error => {
        this.mainMessage = error;
      })
  }
}
