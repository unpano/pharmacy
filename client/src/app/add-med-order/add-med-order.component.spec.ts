import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMedOrderComponent } from './add-med-order.component';

describe('AddMedOrderComponent', () => {
  let component: AddMedOrderComponent;
  let fixture: ComponentFixture<AddMedOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddMedOrderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMedOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
