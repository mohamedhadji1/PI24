import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InternshipfrontComponent } from './internshipfront.component';

describe('InternshipfrontComponent', () => {
  let component: InternshipfrontComponent;
  let fixture: ComponentFixture<InternshipfrontComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InternshipfrontComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InternshipfrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
