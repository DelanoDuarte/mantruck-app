import { async, ComponentFixture, TestBed, tick, fakeAsync } from '@angular/core/testing';
import { AlertComponent } from './alert.component';
import { AlertService } from './alert.service';


describe('AlertComponent', () => {
  let component: AlertComponent;
  let fixture: ComponentFixture<AlertComponent>;
  let service = new AlertService();

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AlertComponent],
      providers: [AlertService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlertComponent);
    component = fixture.componentInstance;
    service = TestBed.get(AlertService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should send a new alert message to subscribers', () => {
    const message = 'New truck added';
    service.alert(message);
    const resultAlert = service.onAlerts();

    resultAlert.subscribe((messageEvent) => {
      expect(messageEvent.message).toEqual(message);
    });
  });

  it('should message auto destroy after 5 seconds', fakeAsync(() => {
    const message = 'New truck added';
    service.alert(message);
    const resultAlert = service.onAlerts();
    tick(6000);
    fixture.detectChanges();

    expect(component.message.length).toBeLessThan(1);
  }));
});
