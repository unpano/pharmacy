import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPharmacyMedsComponent } from './admin-pharmacy-meds.component';

describe('AdminPharmacyMedsComponent', () => {
  let component: AdminPharmacyMedsComponent;
  let fixture: ComponentFixture<AdminPharmacyMedsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminPharmacyMedsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminPharmacyMedsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
