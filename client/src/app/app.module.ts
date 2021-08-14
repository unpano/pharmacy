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




import {MatDividerModule} from '@angular/material/divider';
import {MatTableModule} from '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input'
import { MatToolbar, MatToolbarModule, MatToolbarRow} from '@angular/material/toolbar'
import { MatIcon, MatIconModule} from '@angular/material/icon'
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { LoggedUserHomePageComponent } from './logged-user-home-page/logged-user-home-page.component';

import { HomePageComponent } from './home-page/home-page.component';

import { Ng2SearchPipeModule } from 'ng2-search-filter';



import { PharmacyListComponent } from './pharmacy-list/pharmacy-list.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { PharmacyProfileComponent } from './pharmacy-profile/pharmacy-profile.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    LoggedUserHomePageComponent,
    HomePageComponent,
    PharmacyListComponent,
    UserProfileComponent,
    PharmacyProfileComponent
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
    MatIconModule,
    Ng2SearchPipeModule
  ],
  providers: [MatDatepickerModule],
  bootstrap: [AppComponent]
})

export class AppModule { }
