import { NgModule } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MatSelectModule } from '@angular/material/select';

import {MatNativeDateModule } from '@angular/material/core';

import { HttpClientModule } from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatStepperModule } from '@angular/material/stepper';
import { MatDialogModule } from '@angular/material/dialog';

import { MatFormFieldModule } from '@angular/material/form-field';


import {MatDatepickerModule} from '@angular/material/datepicker';

import { GlobalUseService } from './services/global-use';


import {MatDividerModule} from '@angular/material/divider';
import {MatTableModule} from '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input'
import { MatToolbar, MatToolbarModule, MatToolbarRow} from '@angular/material/toolbar'
import { MatIcon, MatIconModule} from '@angular/material/icon'
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { AdminHomePageComponent } from './admin-home-page/admin-home-page.component';
import { NistagramerHomePageComponent } from './nistagramer-home-page/nistagramer-home-page.component';
import { AgentHomePageComponent } from './agent-home-page/agent-home-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { NistagramerProfileComponent } from './nistagramer-profile/nistagramer-profile.component';
import { PostComponent } from './post/post.component';


import { VerifyProfileComponent } from './verify-profile/verify-profile.component';
import { MyPostsComponent } from './my-posts/my-posts.component';
import { VerificationListComponent } from './verification-list/verification-list.component';
import { UserDetailsComponent } from './user-details/user-details.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    AdminHomePageComponent,
    NistagramerHomePageComponent,
    AgentHomePageComponent,
    HomePageComponent,
    NistagramerProfileComponent,
    PostComponent,
    VerifyProfileComponent,
    MyPostsComponent,
    VerificationListComponent,
    UserDetailsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatStepperModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatDialogModule,
    MatDividerModule,
    MatTableModule,
    MatCardModule,
    MatDatepickerModule,
    MatInputModule,
    MatNativeDateModule,
    MatSelectModule,
    MatToolbarModule,
    MatIconModule
  ],
  providers: [GlobalUseService,MatDatepickerModule],
  bootstrap: [AppComponent]
})

export class AppModule { }
