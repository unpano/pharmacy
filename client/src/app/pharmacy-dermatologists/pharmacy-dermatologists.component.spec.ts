import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacyDermatologistsComponent } from './pharmacy-dermatologists.component';

describe('PharmacyDermatologistsComponent', () => {
  let component: PharmacyDermatologistsComponent;
  let fixture: ComponentFixture<PharmacyDermatologistsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacyDermatologistsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacyDermatologistsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
