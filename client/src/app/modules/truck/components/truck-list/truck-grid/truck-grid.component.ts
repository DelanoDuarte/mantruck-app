import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Truck } from 'src/app/models/Truck';

@Component({
  selector: 'app-truck-grid',
  templateUrl: './truck-grid.component.html',
  styleUrls: ['./truck-grid.component.css'],
})
export class TruckGridComponent implements OnInit {

  @Input()
  trucks: Truck[] = [];

  @Input()
  page: number;

  @Input()
  pageSize: number;

  @Input()
  totalElements = 0;

  @Output()
  pageChanged = new EventEmitter();

  @Output()
  itemsPerPageChanged = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
  }

  onPageChanged(page) {
    this.pageChanged.emit(page);
  }

  itemsPerPageEvent() {
    this.itemsPerPageChanged.emit(this.pageSize);
  }
}
