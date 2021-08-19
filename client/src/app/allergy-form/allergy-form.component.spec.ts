import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllergyFormComponent } from './allergy-form.component';

describe('AllergyFormComponent', () => {
  let component: AllergyFormComponent;
  let fixture: ComponentFixture<AllergyFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllergyFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllergyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
