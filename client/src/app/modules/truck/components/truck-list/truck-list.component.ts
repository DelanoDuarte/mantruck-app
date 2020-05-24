import { Component, OnInit } from '@angular/core';
import { Truck } from 'src/app/models/Truck';
import { TruckService } from 'src/app/services/truck.service';

@Component({
  selector: 'app-truck-list',
  templateUrl: './truck-list.component.html',
  styleUrls: ['./truck-list.component.css']
})
export class TruckListComponent implements OnInit {

  trucks: Truck[] = [];

  constructor(private truckService: TruckService) { }

  ngOnInit(): void {
    this.fetchAllTrucks();
  }

  fetchAllTrucks(): void {
    this.truckService.findAll().subscribe(trucks => this.trucks = trucks);
  }

}
