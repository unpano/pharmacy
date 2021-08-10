import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { AdminHomePageComponent } from './admin-home-page/admin-home-page.component';
import { NistagramerHomePageComponent } from './nistagramer-home-page/nistagramer-home-page.component';
import { AgentHomePageComponent } from './agent-home-page/agent-home-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { MyPostsComponent } from './my-posts/my-posts.component';
import { VerificationListComponent } from './verification-list/verification-list.component';

const routes: Routes = [
  {path: 'loggedNistagramer', component: NistagramerHomePageComponent},
  {path: 'loggedAdmin', component: AdminHomePageComponent},
  {path: 'loggedAgent', component: AgentHomePageComponent},
  {path: '', component: HomePageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'myPosts', component: MyPostsComponent},
  {path: 'requestsList', component: VerificationListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
