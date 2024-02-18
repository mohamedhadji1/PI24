import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskfrontComponent } from './taskfront.component';

describe('TaskfrontComponent', () => {
  let component: TaskfrontComponent;
  let fixture: ComponentFixture<TaskfrontComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TaskfrontComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TaskfrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
