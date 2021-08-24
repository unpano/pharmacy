import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDermAppointmentsComponent } from './admin-derm-appointments.component';

describe('AdminDermAppointmentsComponent', () => {
  let component: AdminDermAppointmentsComponent;
  let fixture: ComponentFixture<AdminDermAppointmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminDermAppointmentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminDermAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
