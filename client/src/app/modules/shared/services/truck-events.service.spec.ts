import { TestBed } from '@angular/core/testing';

import { TruckEventsService } from './truck-events.service';

describe('TruckEventsService', () => {
  let service: TruckEventsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TruckEventsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
