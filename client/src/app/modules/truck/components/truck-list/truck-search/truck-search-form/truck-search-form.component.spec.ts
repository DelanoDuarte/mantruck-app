import { HttpClientModule } from '@angular/common/http';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { TruckSearchFormComponent } from './truck-search-form.component';


describe('TruckSearchFormComponent', () => {
  let component: TruckSearchFormComponent;
  let fixture: ComponentFixture<TruckSearchFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TruckSearchFormComponent],
      imports: [HttpClientModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TruckSearchFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should emit truck object on change any value', () => {
    component.truckForm.patchValue({ model: 'Test' });
    spyOn(component.truck, 'emit');

    component.onChangeValue();

    expect(component.truck.emit).toHaveBeenCalled();
  });
});
