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

import { WriteComplaintComponent } from './write-complaint/write-complaint.component';
import { FutureDermAppointmentListComponent } from './future-derm-appointment-list/future-derm-appointment-list.component';
import { PastDermAppointmentListComponent } from './past-derm-appointment-list/past-derm-appointment-list.component';
import { SchedulePharmacistAppointmentComponent } from './schedule-pharmacist-appointment/schedule-pharmacist-appointment.component';
import { ReservationListComponent } from './reservation-list/reservation-list.component';
import { RateComponent } from './rate/rate.component';
import { PrescriptionListComponent } from './prescription-list/prescription-list.component';
import { DermatologistListComponent } from './dermatologist-list/dermatologist-list.component';
import { PharmacistListComponent } from './pharmacist-list/pharmacist-list.component';
import { MedicationRateListComponent } from './medication-rate-list/medication-rate-list.component';
import { PharmacyRateListComponent } from './pharmacy-rate-list/pharmacy-rate-list.component';


const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'meds', component: MedsComponent},
  {path: 'profile', component: UserProfileComponent},
  {path: 'pharmacyList', component: PharmacyListComponent},
  {path: 'loggedUserHomePage', component: LoggedUserHomePageComponent},
  {path: 'futureDermAppointments', component: FutureDermAppointmentListComponent},
  {path: 'pastDermAppointments', component: PastDermAppointmentListComponent},
  {path: 'schedulePharmacistAppointment', component: SchedulePharmacistAppointmentComponent},
  {path: 'reservations', component: ReservationListComponent},
  {path: 'writeComplaint', component: WriteComplaintComponent},
  {path: 'rate', component: RateComponent},
  {path: 'prescriptions', component: PrescriptionListComponent},
  {path: 'rateDermatologist', component: DermatologistListComponent},
  {path: 'ratePharmacist', component: PharmacistListComponent},
  {path: 'rateMedication', component: MedicationRateListComponent},
  {path: 'ratePharmacy', component: PharmacyRateListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
