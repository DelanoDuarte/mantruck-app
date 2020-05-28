import { Component, OnInit, Input } from '@angular/core';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Truck } from 'src/app/models/Truck';
import { TruckService } from 'src/app/services/truck.service';
import { AlertService } from 'src/app/modules/shared/alert/alert.service';
import { TruckEventsService } from 'src/app/modules/shared/services/truck-events.service';

@Component({
  selector: 'app-truck-delete-modal',
  templateUrl: './truck-delete-modal.component.html',
  styleUrls: ['./truck-delete-modal.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class TruckDeleteModalComponent implements OnInit {

  @Input()
  truck: Truck;

  constructor(
    private modalService: NgbModal,
    private truckService: TruckService,
    private alertService: AlertService,
    private truckEventService: TruckEventsService) { }

  ngOnInit(): void { }

  openDeleteModal(content: any) {
    this.modalService.open(content);
  }

  deleteTruck() {
    try {
      this.truckService.deleteOne(this.truck.id).subscribe(() => {
        this.modalService.dismissAll();
        this.truckEventService.updateTruckList();
        this.alertService.alert('Truck successfully deleted', 'success');
      });
    } catch (error) {
      this.alertService.alert('Oh no, something bad happened', 'error');
    }
  }
}
