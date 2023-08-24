import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketResponsesComponent } from './ticket-responses.component';

describe('TicketResponsesComponent', () => {
  let component: TicketResponsesComponent;
  let fixture: ComponentFixture<TicketResponsesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TicketResponsesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TicketResponsesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
