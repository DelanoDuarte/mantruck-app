import { HttpClientModule } from '@angular/common/http';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { TruckDeleteModalComponent } from './truck-delete-modal.component';


describe('TruckDeleteModalComponent', () => {
  let component: TruckDeleteModalComponent;
  let fixture: ComponentFixture<TruckDeleteModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TruckDeleteModalComponent],
      imports: [HttpClientModule],
      providers: [
        NgbModal,
        NgbModalConfig],
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TruckDeleteModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
