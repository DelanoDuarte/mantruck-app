import { HttpClientModule } from '@angular/common/http';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { TruckCreateFormComponent } from './truck-create-form.component';


describe('TruckCreateFormComponent', () => {
  let component: TruckCreateFormComponent;
  let fixture: ComponentFixture<TruckCreateFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TruckCreateFormComponent],
      imports: [HttpClientModule]
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

  it('should emit truck object on form submit', () => {
    component.truckForm.setValue({ model: 'Test', enginePower: 450, fuel: 'DIESEL', range: 'HEAVY_RANGE', segments: [] });
    spyOn(component.truck, 'emit');

    component.onSubmitForm();

    expect(component.truck.emit).toHaveBeenCalled();
  });
});
