import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TruckGridComponent } from './truck-grid.component';

describe('TruckGridComponent', () => {
  let component: TruckGridComponent;
  let fixture: ComponentFixture<TruckGridComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TruckGridComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TruckGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
