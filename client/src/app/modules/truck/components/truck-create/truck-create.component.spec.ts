import { HttpClientModule } from '@angular/common/http';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { TruckCreateComponent } from './truck-create.component';
import { Truck } from 'src/app/models/Truck';
import { Router } from '@angular/router';
import { TruckService } from 'src/app/services/truck.service';


describe('TruckCreateComponent', () => {
  let component: TruckCreateComponent;
  let fixture: ComponentFixture<TruckCreateComponent>;
  let router: Router;
  let service: TruckService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TruckCreateComponent],
      imports: [HttpClientModule, RouterTestingModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TruckCreateComponent);
    component = fixture.componentInstance;
    router = TestBed.get(Router);
    service = TestBed.get(TruckService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
