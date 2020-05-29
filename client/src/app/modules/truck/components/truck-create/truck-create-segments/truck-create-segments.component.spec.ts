import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TruckCreateSegmentsComponent } from './truck-create-segments.component';
import { HttpClientModule } from '@angular/common/http';
import { Segment } from 'src/app/models/Segment';

describe('TruckCreateSegmentsComponent', () => {
  let component: TruckCreateSegmentsComponent;
  let fixture: ComponentFixture<TruckCreateSegmentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TruckCreateSegmentsComponent],
      imports: [HttpClientModule]
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

  it('should emit segments on select segments', () => {
    const segment = new Segment('Food');
    spyOn(component.segmentsSelected, 'emit');

    component.emitSegmentsSelected([segment]);

    expect(component.segmentsSelected.emit).toHaveBeenCalled();
  });
});
