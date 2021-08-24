import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPharmacyPharmacistsComponent } from './admin-pharmacy-pharmacists.component';

describe('AdminPharmacyPharmacistsComponent', () => {
  let component: AdminPharmacyPharmacistsComponent;
  let fixture: ComponentFixture<AdminPharmacyPharmacistsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminPharmacyPharmacistsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminPharmacyPharmacistsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
