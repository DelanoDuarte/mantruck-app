import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-truck-create-segments',
  templateUrl: './truck-create-segments.component.html',
  styleUrls: ['./truck-create-segments.component.css']
})
export class TruckCreateSegmentsComponent implements OnInit {

  segments: any[] = [];

  constructor() { }

  ngOnInit(): void {
  }

}
