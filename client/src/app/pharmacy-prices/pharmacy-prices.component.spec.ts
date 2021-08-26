import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacyPricesComponent } from './pharmacy-prices.component';

describe('PharmacyPricesComponent', () => {
  let component: PharmacyPricesComponent;
  let fixture: ComponentFixture<PharmacyPricesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacyPricesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacyPricesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
