import { Component, OnInit } from '@angular/core';
import { Truck } from 'src/app/models/Truck';
import { TruckService } from 'src/app/services/truck.service';
import { Subscription } from 'rxjs';
import { TruckEventsService } from 'src/app/modules/shared/services/truck-events.service';

@Component({
  selector: 'app-truck-list',
  templateUrl: './truck-list.component.html',
  styleUrls: ['./truck-list.component.css']
})
export class TruckListComponent implements OnInit {

  trucks: Truck[] = [];

  truckSubscription: Subscription;

  constructor(private truckService: TruckService, private truckEventService: TruckEventsService) {
    this.truckSubscription = this.truckEventService.getEventAfterEditTruck().subscribe(() => {
      this.fetchAllTrucks();
    });
  }

  ngOnInit(): void {
    this.fetchAllTrucks();
  }

  fetchAllTrucks(): void {
    this.truckService.findAll().subscribe(trucks => this.trucks = trucks);
  }

}
