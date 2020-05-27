import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TruckEventsService {

  private afterEditTruckSubject = new Subject();

  constructor() { }

  fireEventAfterEditTruc() {
    this.afterEditTruckSubject.next();
  }

  getEventAfterEditTruck() {
    return this.afterEditTruckSubject.asObservable();
  }
}
