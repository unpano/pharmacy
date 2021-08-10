import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentHomePageComponent } from './agent-home-page.component';

describe('AgentHomePageComponent', () => {
  let component: AgentHomePageComponent;
  let fixture: ComponentFixture<AgentHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgentHomePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgentHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
