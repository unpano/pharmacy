import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FutureDermAppointmentListComponent } from './future-derm-appointment-list.component';

describe('FutureDermAppointmentListComponent', () => {
  let component: FutureDermAppointmentListComponent;
  let fixture: ComponentFixture<FutureDermAppointmentListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FutureDermAppointmentListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FutureDermAppointmentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
