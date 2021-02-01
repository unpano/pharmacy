import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Constants } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  ngOnInit(): void {

  }

  findAllUsers(): Observable<any> {
    return this.http.get(Constants.FIND_ALL_USERS);
  }
}
