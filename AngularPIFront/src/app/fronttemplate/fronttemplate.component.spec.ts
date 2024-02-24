import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FronttemplateComponent } from './fronttemplate.component';

describe('FronttemplateComponent', () => {
  let component: FronttemplateComponent;
  let fixture: ComponentFixture<FronttemplateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FronttemplateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FronttemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
