import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketIndividualViewComponent } from './ticket-individual-view.component';

describe('TicketIndividualViewComponent', () => {
  let component: TicketIndividualViewComponent;
  let fixture: ComponentFixture<TicketIndividualViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TicketIndividualViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TicketIndividualViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
