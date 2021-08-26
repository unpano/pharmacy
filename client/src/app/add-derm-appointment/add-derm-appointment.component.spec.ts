import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDermAppointmentComponent } from './add-derm-appointment.component';

describe('AddDermAppointmentComponent', () => {
  let component: AddDermAppointmentComponent;
  let fixture: ComponentFixture<AddDermAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDermAppointmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddDermAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
