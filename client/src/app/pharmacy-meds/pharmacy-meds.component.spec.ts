import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacyMedsComponent } from './pharmacy-meds.component';

describe('PharmacyMedsComponent', () => {
  let component: PharmacyMedsComponent;
  let fixture: ComponentFixture<PharmacyMedsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacyMedsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacyMedsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
