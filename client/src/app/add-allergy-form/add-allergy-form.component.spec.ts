import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAllergyFormComponent } from './add-allergy-form.component';

describe('AddAllergyFormComponent', () => {
  let component: AddAllergyFormComponent;
  let fixture: ComponentFixture<AddAllergyFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddAllergyFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAllergyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
