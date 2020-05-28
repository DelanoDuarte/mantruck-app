import { Component, Input, OnInit } from '@angular/core';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { Truck } from 'src/app/models/Truck';
import { TruckService } from 'src/app/services/truck.service';
import { TruckEventsService } from 'src/app/modules/shared/services/truck-events.service';
import { AlertService } from 'src/app/modules/shared/alert/alert.service';


@Component({
  selector: 'app-truck-modal-edit',
  templateUrl: './truck-modal-edit.component.html',
  styleUrls: ['./truck-modal-edit.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class TruckModalEditComponent implements OnInit {

  @Input()
  truck: Truck;

  constructor(
    private modalService: NgbModal,
    private truckService: TruckService,
    private truckEventService: TruckEventsService,
    private alertService: AlertService) { }

  ngOnInit(): void {
  }

  editedTruck(truck: Truck) {
    try {
      truck.id = this.truck.id;
      this.truckService.save(truck).subscribe(truckSaved => {
        this.modalService.dismissAll();
        this.truckEventService.updateTruckList();
        this.alertService.alert('Truck successfully edited', 'success');
      });
    } catch (error) {

    }
  }

  cancel() {
    this.modalService.dismissAll();
  }

  openEditModal(content: any) {
    this.modalService.open(content, { size: 'lg' });
  }
}
