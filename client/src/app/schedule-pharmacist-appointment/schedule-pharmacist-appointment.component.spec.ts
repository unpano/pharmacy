import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SchedulePharmacistAppointmentComponent } from './schedule-pharmacist-appointment.component';

describe('SchedulePharmacistAppointmentComponent', () => {
  let component: SchedulePharmacistAppointmentComponent;
  let fixture: ComponentFixture<SchedulePharmacistAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SchedulePharmacistAppointmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SchedulePharmacistAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
