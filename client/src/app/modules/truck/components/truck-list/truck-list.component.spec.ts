import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TruckListComponent } from './truck-list.component';
import { HttpClientModule } from '@angular/common/http';

describe('TruckListComponent', () => {
  let component: TruckListComponent;
  let fixture: ComponentFixture<TruckListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TruckListComponent],
      imports: [HttpClientModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TruckListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
