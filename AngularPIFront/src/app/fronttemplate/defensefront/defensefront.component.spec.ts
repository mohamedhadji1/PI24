import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefensefrontComponent } from './defensefront.component';

describe('DefensefrontComponent', () => {
  let component: DefensefrontComponent;
  let fixture: ComponentFixture<DefensefrontComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DefensefrontComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DefensefrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
