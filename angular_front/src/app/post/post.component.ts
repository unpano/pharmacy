import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from '../dto/post';
import { FindPostsService } from './find-posts-service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  header : String[] = ['Picture Path', 'Description'];
  

  posts: Post[] = [];
  
  constructor(private postService:FindPostsService, private router: Router) { }

  ngOnInit(): void {
    this.postService.findPosts().subscribe(
      res => this.posts = res );
  }


}
