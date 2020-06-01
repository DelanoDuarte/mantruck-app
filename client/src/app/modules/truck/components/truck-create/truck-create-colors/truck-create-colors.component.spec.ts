import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TruckCreateColorsComponent } from './truck-create-colors.component';

describe('TruckCreateColorsComponent', () => {
  let component: TruckCreateColorsComponent;
  let fixture: ComponentFixture<TruckCreateColorsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TruckCreateColorsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TruckCreateColorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
