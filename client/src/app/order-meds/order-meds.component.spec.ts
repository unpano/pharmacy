import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderMedsComponent } from './order-meds.component';

describe('OrderMedsComponent', () => {
  let component: OrderMedsComponent;
  let fixture: ComponentFixture<OrderMedsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrderMedsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderMedsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
