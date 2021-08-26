import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicationRateListComponent } from './medication-rate-list.component';

describe('MedicationRateListComponent', () => {
  let component: MedicationRateListComponent;
  let fixture: ComponentFixture<MedicationRateListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicationRateListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicationRateListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
