import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentDetailsTableComponent } from './agent-details-table.component';

describe('AgentDetailsTableComponent', () => {
  let component: AgentDetailsTableComponent;
  let fixture: ComponentFixture<AgentDetailsTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgentDetailsTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgentDetailsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
