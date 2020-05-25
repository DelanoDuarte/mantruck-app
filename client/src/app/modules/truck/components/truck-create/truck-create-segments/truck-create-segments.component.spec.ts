import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TruckCreateSegmentsComponent } from './truck-create-segments.component';

describe('TruckCreateSegmentsComponent', () => {
  let component: TruckCreateSegmentsComponent;
  let fixture: ComponentFixture<TruckCreateSegmentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TruckCreateSegmentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TruckCreateSegmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
