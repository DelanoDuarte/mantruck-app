import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Truck } from 'src/app/models/Truck';
import { TruckService } from 'src/app/services/truck.service';

@Component({
  selector: 'app-truck-search',
  templateUrl: './truck-search.component.html',
  styleUrls: ['./truck-search.component.css']
})
export class TruckSearchComponent implements OnInit {

  @Output()
  truckToSearch = new EventEmitter();

  constructor(private truckService: TruckService) { }

  ngOnInit(): void {
  }

  searchTruck(truck: Truck) {
    this.truckToSearch.emit(truck);
  }

}
