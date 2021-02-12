import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SavecustomerComponent } from './savecustomer.component';

describe('SavecustomerComponent', () => {
  let component: SavecustomerComponent;
  let fixture: ComponentFixture<SavecustomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SavecustomerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SavecustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
