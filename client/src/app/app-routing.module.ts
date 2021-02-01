import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './components/user/user.component'
import { LoginComponent } from './components/login/login.component' 
import { AuthenticationGuard } from './guards/authentication.guard';

//canActivate nam govori ko moze da aktivira komponentu, po default-u mogu svi
const routes: Routes = [
  { path: 'users', component: UserComponent, canActivate: [AuthenticationGuard]},
  { path: 'login', component: LoginComponent},
  { path: '** ', redirectTo: '/login'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
