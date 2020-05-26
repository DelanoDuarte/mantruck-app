import { Component, Input, OnInit } from '@angular/core';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { Truck } from 'src/app/models/Truck';


@Component({
  selector: 'app-truck-modal-edit',
  templateUrl: './truck-modal-edit.component.html',
  styleUrls: ['./truck-modal-edit.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class TruckModalEditComponent implements OnInit {

  @Input()
  truck: Truck;

  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  editedTruck(truck: Truck) {
    console.log(truck);
  }

  openEditModal(content: any) {
    this.modalService.open(content);
  }
}
