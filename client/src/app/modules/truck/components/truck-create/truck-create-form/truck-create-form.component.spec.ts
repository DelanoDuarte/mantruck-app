import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TruckCreateFormComponent } from './truck-create-form.component';

describe('TruckCreateFormComponent', () => {
  let component: TruckCreateFormComponent;
  let fixture: ComponentFixture<TruckCreateFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TruckCreateFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TruckCreateFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
