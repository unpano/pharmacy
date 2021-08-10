import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NistagramerProfileComponent } from './nistagramer-profile.component';

describe('NistagramerProfileComponent', () => {
  let component: NistagramerProfileComponent;
  let fixture: ComponentFixture<NistagramerProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NistagramerProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NistagramerProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
