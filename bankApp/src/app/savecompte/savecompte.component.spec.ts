import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SavecompteComponent } from './savecompte.component';

describe('SavecompteComponent', () => {
  let component: SavecompteComponent;
  let fixture: ComponentFixture<SavecompteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SavecompteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SavecompteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
