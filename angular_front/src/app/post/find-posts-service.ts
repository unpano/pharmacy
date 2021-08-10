import { HttpClient, HttpHeaders } from '@angular/common/http';
import { asLiteral } from '@angular/compiler/src/render3/view/util';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';
import { Post } from '../dto/post';
import { Endpoint } from '../util/endpoints-enum';
 
@Injectable({providedIn:'root'})
export class FindPostsService {


  
  posts: Post[] = [];

  endpoint = Endpoint;

  constructor(private http: HttpClient) {
  }
 
  

findPosts(){

    return this.http
      .get(this.endpoint.POST+'post/' )
      .pipe(
        map(returnedArrayOfPosts => {
          const posts = [];
          
          for (const id in returnedArrayOfPosts) {
            posts.push(returnedArrayOfPosts[id]) 
          } 
          console.log(posts)         
          return posts;
        })
      )
  }





}