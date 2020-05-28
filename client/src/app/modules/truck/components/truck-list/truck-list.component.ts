import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Truck } from 'src/app/models/Truck';
import { TruckEventsService } from 'src/app/modules/shared/services/truck-events.service';
import { TruckService } from 'src/app/services/truck.service';

@Component({
  selector: 'app-truck-list',
  templateUrl: './truck-list.component.html',
  styleUrls: ['./truck-list.component.css']
})
export class TruckListComponent implements OnInit, OnDestroy {

  trucks: Truck[] = [];
  truck: Truck;
  page = 1;

  truckSubscription: Subscription;

  constructor(private truckService: TruckService, private truckEventService: TruckEventsService) {
    this.truckSubscription = this.truckEventService.listTruckListener().subscribe(() => {
      this.fetchAllTrucks();
    });
  }

  ngOnInit(): void {
    this.fetchAllTrucks();
  }

  ngOnDestroy(): void {
    this.truckSubscription.unsubscribe();
  }

  fetchAllTrucks(): void {
    if (this.truck) {
      this.searchTruck(this.truck);
    }
    this.truckService.findAll().subscribe(trucks => this.trucks = trucks);
  }

  mapTruck(truck: Truck) {
    this.truck = truck;
  }

  clearSearchFilters() {
    this.truck = null;
    this.truckEventService.clearFiltersForm();
  }

  searchTruck(truck: Truck) {
    this.truckService.findAllWithFilterAndPaginated(this.truck, this.page - 1, 10).subscribe(response => {
      this.trucks = response.content;
    });
  }

}
