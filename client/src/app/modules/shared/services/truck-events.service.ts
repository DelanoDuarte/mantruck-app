import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TruckEventsService {

  private updateListTruck = new Subject();
  private resetFormSearchTruck = new Subject();

  constructor() { }

  updateTruckList() {
    this.updateListTruck.next();
  }

  listTruckListener() {
    return this.updateListTruck.asObservable();
  }

  clearFiltersForm() {
    this.resetFormSearchTruck.next();
  }

  searchTruckFormListener() {
    return this.resetFormSearchTruck.asObservable();
  }
}
