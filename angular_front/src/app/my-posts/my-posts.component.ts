import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Post } from '../dto/post';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';

@Component({
  selector: 'app-my-posts',
  templateUrl: './my-posts.component.html',
  styleUrls: ['./my-posts.component.css']
})
export class MyPostsComponent implements OnInit {

  name: String = Global.loggedUser.username
  posts: Post[] = [];

  endpoint = Endpoint;

  constructor(private http: HttpClient) { }

  ngOnInit(): any {
    return this.http
      .get(this.endpoint.POST+'myPosts/' + Global.loggedUser.id )
      .pipe(
        map(returnedArrayOfPosts => {
         
          //console.log(returnedArrayOfPosts)         
          return returnedArrayOfPosts;
        })
      ).subscribe()
  }

}
