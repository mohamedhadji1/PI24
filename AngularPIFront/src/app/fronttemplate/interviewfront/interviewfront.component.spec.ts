import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InterviewfrontComponent } from './interviewfront.component';

describe('InterviewfrontComponent', () => {
  let component: InterviewfrontComponent;
  let fixture: ComponentFixture<InterviewfrontComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InterviewfrontComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InterviewfrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
