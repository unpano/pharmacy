import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDermApp2Component } from './add-derm-app2.component';

describe('AddDermApp2Component', () => {
  let component: AddDermApp2Component;
  let fixture: ComponentFixture<AddDermApp2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDermApp2Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddDermApp2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
