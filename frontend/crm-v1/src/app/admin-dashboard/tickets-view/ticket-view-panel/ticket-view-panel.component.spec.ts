import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketViewPanelComponent } from './ticket-view-panel.component';

describe('TicketViewPanelComponent', () => {
  let component: TicketViewPanelComponent;
  let fixture: ComponentFixture<TicketViewPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TicketViewPanelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TicketViewPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
