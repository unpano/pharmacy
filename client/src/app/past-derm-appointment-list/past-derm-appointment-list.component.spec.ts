import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PastDermAppointmentListComponent } from './past-derm-appointment-list.component';

describe('PastDermAppointmentListComponent', () => {
  let component: PastDermAppointmentListComponent;
  let fixture: ComponentFixture<PastDermAppointmentListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PastDermAppointmentListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PastDermAppointmentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
