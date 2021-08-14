import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoggedUserHomePageComponent } from './logged-user-home-page.component';

describe('NistagramerHomePageComponent', () => {
  let component: LoggedUserHomePageComponent;
  let fixture: ComponentFixture<LoggedUserHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoggedUserHomePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoggedUserHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
