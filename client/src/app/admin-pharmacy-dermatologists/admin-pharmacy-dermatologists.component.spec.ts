import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPharmacyDermatologistsComponent } from './admin-pharmacy-dermatologists.component';

describe('AdminPharmacyDermatologistsComponent', () => {
  let component: AdminPharmacyDermatologistsComponent;
  let fixture: ComponentFixture<AdminPharmacyDermatologistsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminPharmacyDermatologistsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminPharmacyDermatologistsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
