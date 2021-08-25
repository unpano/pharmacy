import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistListComponent } from './dermatologist-list.component';

describe('DermatologistListComponent', () => {
  let component: DermatologistListComponent;
  let fixture: ComponentFixture<DermatologistListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
