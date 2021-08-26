import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacyRateListComponent } from './pharmacy-rate-list.component';

describe('PharmacyRateListComponent', () => {
  let component: PharmacyRateListComponent;
  let fixture: ComponentFixture<PharmacyRateListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacyRateListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacyRateListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
