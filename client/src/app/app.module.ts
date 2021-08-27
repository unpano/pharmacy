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


import { Ng2SearchPipeModule } from 'ng2-search-filter';



import { PharmacyListComponent } from './pharmacy-list/pharmacy-list.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { PharmacyProfileComponent } from './pharmacy-profile/pharmacy-profile.component';
import { MedListComponent } from './med-list/med-list.component';
import { DermAppointmentListComponent } from './derm-appointment-list/derm-appointment-list.component';
import { HeaderComponent } from './header/header.component';
import { HeaderLComponent } from './header-l/header-l.component';
import { HomePageComponent } from './home-page/home-page.component';
import { LoggedUserHomePageComponent } from './logged-user-home-page/logged-user-home-page.component';
import { PharmacyMedsComponent } from './pharmacy-meds/pharmacy-meds.component';
import { MedsComponent } from './meds/meds.component';
import { AddAllergyFormComponent } from './add-allergy-form/add-allergy-form.component';

import { AdminPageComponent } from './admin-page/admin-page.component';
import { PharmacyDetailsComponent } from './pharmacy-details/pharmacy-details.component';

import { FutureDermAppointmentListComponent } from './future-derm-appointment-list/future-derm-appointment-list.component';
import { PastDermAppointmentListComponent } from './past-derm-appointment-list/past-derm-appointment-list.component';
import { SchedulePharmacistAppointmentComponent } from './schedule-pharmacist-appointment/schedule-pharmacist-appointment.component';
import { PickDateComponent } from './pick-date/pick-date.component';
import { ReservationListComponent } from './reservation-list/reservation-list.component';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import { MatSortModule } from '@angular/material/sort';

import { AdminPharmacyMedsComponent } from './admin-pharmacy-meds/admin-pharmacy-meds.component';
import { AdminPharmacyPharmacistsComponent } from './admin-pharmacy-pharmacists/admin-pharmacy-pharmacists.component';
import { AdminDermAppointmentsComponent } from './admin-derm-appointments/admin-derm-appointments.component';
import { AdminHeaderComponent } from './admin-header/admin-header.component';
import { PharmacyPricesComponent } from './pharmacy-prices/pharmacy-prices.component';
import { AddDermAppointmentComponent } from './add-derm-appointment/add-derm-appointment.component';


import { WriteComplaintComponent } from './write-complaint/write-complaint.component';
import { RateComponent } from './rate/rate.component';
import { PrescriptionListComponent } from './prescription-list/prescription-list.component';
import { PickWhomToRateComponent } from './pick-whom-to-rate/pick-whom-to-rate.component';
import { DermatologistListComponent } from './dermatologist-list/dermatologist-list.component';
import { PharmacistListComponent } from './pharmacist-list/pharmacist-list.component';
import { PharmacyRateListComponent } from './pharmacy-rate-list/pharmacy-rate-list.component';
import { MedicationRateListComponent } from './medication-rate-list/medication-rate-list.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    PharmacyListComponent,
    UserProfileComponent,
    PharmacyProfileComponent,
    MedListComponent,
    DermAppointmentListComponent,
    HeaderComponent,
    HeaderLComponent,
    HomePageComponent,
    LoggedUserHomePageComponent,
    PharmacyMedsComponent,
    MedsComponent,
    AddAllergyFormComponent,

    AdminPageComponent,
    PharmacyDetailsComponent,

    FutureDermAppointmentListComponent,
    PastDermAppointmentListComponent,
    SchedulePharmacistAppointmentComponent,
    PickDateComponent,
    ReservationListComponent,
    AdminPharmacyMedsComponent,
    AdminPharmacyPharmacistsComponent,
    AdminDermAppointmentsComponent,
    AdminHeaderComponent,
    PharmacyPricesComponent,
    AddDermAppointmentComponent,



    WriteComplaintComponent,
    RateComponent,
    PrescriptionListComponent,
    PickWhomToRateComponent,
    DermatologistListComponent,
    PharmacistListComponent,
    PharmacyRateListComponent,
    MedicationRateListComponent

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
    Ng2SearchPipeModule,
    MatSortModule,
    NgxMaterialTimepickerModule,
    MatTableModule,
    CommonModule
  ],
  providers: [MatDatepickerModule],
  bootstrap: [AppComponent],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})

export class AppModule { }
