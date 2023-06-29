import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentsAnalyticsComponent } from './agents-analytics.component';

describe('AgentsAnalyticsComponent', () => {
  let component: AgentsAnalyticsComponent;
  let fixture: ComponentFixture<AgentsAnalyticsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgentsAnalyticsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgentsAnalyticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
