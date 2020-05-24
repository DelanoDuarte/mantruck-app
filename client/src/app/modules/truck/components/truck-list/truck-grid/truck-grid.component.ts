import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Truck } from 'src/app/models/Truck';

@Component({
  selector: 'app-truck-grid',
  templateUrl: './truck-grid.component.html',
  styleUrls: ['./truck-grid.component.css']
})
export class TruckGridComponent implements OnInit {

  @Input()
  trucks: Truck[] = [];

  @Output()
  selectedTruck = new EventEmitter<any>();

  constructor() { }

  ngOnInit(): void {
  }

}
