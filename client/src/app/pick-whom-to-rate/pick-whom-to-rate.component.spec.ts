import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PickWhomToRateComponent } from './pick-whom-to-rate.component';

describe('PickWhomToRateComponent', () => {
  let component: PickWhomToRateComponent;
  let fixture: ComponentFixture<PickWhomToRateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PickWhomToRateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PickWhomToRateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
