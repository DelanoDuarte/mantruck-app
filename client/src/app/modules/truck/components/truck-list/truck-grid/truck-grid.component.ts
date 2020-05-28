import { Component, Input, OnInit } from '@angular/core';
import { Truck } from 'src/app/models/Truck';

@Component({
  selector: 'app-truck-grid',
  templateUrl: './truck-grid.component.html',
  styleUrls: ['./truck-grid.component.css']
})
export class TruckGridComponent implements OnInit {

  @Input()
  trucks: Truck[] = [];

  constructor() { }

  ngOnInit(): void {
  }

}
