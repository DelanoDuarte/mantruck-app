import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TruckModalEditComponent } from './truck-modal-edit.component';
import { HttpClientModule } from '@angular/common/http';

describe('TruckModalEditComponent', () => {
  let component: TruckModalEditComponent;
  let fixture: ComponentFixture<TruckModalEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TruckModalEditComponent],
      imports: [HttpClientModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TruckModalEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
