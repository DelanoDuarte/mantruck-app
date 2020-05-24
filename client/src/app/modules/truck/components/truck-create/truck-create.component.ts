import { Component, OnInit } from '@angular/core';
import { Truck } from 'src/app/models/Truck';

@Component({
  selector: 'app-truck-create',
  templateUrl: './truck-create.component.html',
  styleUrls: ['./truck-create.component.css']
})
export class TruckCreateComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {

  }

  mapTruckToSave(truck: Truck) {
    console.log(truck);
  }

}
