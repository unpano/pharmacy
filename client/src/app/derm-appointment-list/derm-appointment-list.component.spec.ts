import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermAppointmentListComponent } from './derm-appointment-list.component';

describe('DermAppointmentListComponent', () => {
  let component: DermAppointmentListComponent;
  let fixture: ComponentFixture<DermAppointmentListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermAppointmentListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermAppointmentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
