import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NistagramerHomePageComponent } from './nistagramer-home-page.component';

describe('NistagramerHomePageComponent', () => {
  let component: NistagramerHomePageComponent;
  let fixture: ComponentFixture<NistagramerHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NistagramerHomePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NistagramerHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
