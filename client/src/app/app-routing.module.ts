import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';

import { UserProfileComponent } from './user-profile/user-profile.component';
import { MedListComponent } from './med-list/med-list.component';
import { PharmacyListComponent } from './pharmacy-list/pharmacy-list.component';
import { HomePageComponent } from './home-page/home-page.component';
import { LoggedUserHomePageComponent } from './logged-user-home-page/logged-user-home-page.component';
import { MedsComponent } from './meds/meds.component';
import { DermAppointmentListComponent } from './derm-appointment-list/derm-appointment-list.component';

import { AdminPageComponent } from './admin-page/admin-page.component';
import { PharmacyDetailsComponent } from './pharmacy-details/pharmacy-details.component';

import { FutureDermAppointmentListComponent } from './future-derm-appointment-list/future-derm-appointment-list.component';
import { PastDermAppointmentListComponent } from './past-derm-appointment-list/past-derm-appointment-list.component';
import { SchedulePharmacistAppointmentComponent } from './schedule-pharmacist-appointment/schedule-pharmacist-appointment.component';
import { ReservationListComponent } from './reservation-list/reservation-list.component';


const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'meds', component: MedsComponent},
  {path: 'profile', component: UserProfileComponent},
  {path: 'pharmacyList', component: PharmacyListComponent},

  {path: 'adminPage', component: AdminPageComponent},
  {path: 'loggedUserHomePage', component: LoggedUserHomePageComponent},
  {path: 'pharmacyDetails', component: PharmacyDetailsComponent},

  {path: 'loggedUserHomePage', component: LoggedUserHomePageComponent},
  {path: 'futureDermAppointments', component: FutureDermAppointmentListComponent},
  {path: 'pastDermAppointments', component: PastDermAppointmentListComponent},
  {path: 'schedulePharmacistAppointment', component: SchedulePharmacistAppointmentComponent},
  {path: 'reservations', component: ReservationListComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})


export class AppRoutingModule { }
