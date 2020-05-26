import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Segment } from 'src/app/models/Segment';
import { Truck } from 'src/app/models/Truck';
import { TruckService } from 'src/app/services/truck.service';

@Component({
  selector: 'app-truck-create-form',
  templateUrl: './truck-create-form.component.html',
  styleUrls: ['./truck-create-form.component.css']
})
export class TruckCreateFormComponent implements OnInit {

  @Input()
  edit: boolean;

  @Input()
  truckToEdit: Truck;

  fuels: string[] = [];
  ranges: string[] = [];

  truckForm = new FormGroup({
    model: new FormControl('', {
      validators: [Validators.required]
    }),
    enginePower: new FormControl('', {
      validators: [Validators.required]
    }),
    fuel: new FormControl('', {
      validators: [Validators.required]
    }),
    range: new FormControl('', {
      validators: [Validators.required]
    }),
    segments: new FormControl([])
  });

  @Output()
  truck = new EventEmitter<Truck>();

  constructor(private truckService: TruckService) { }

  ngOnInit(): void {
    if (this.edit) {
      this.truckForm.setValue({
        model: this.truckToEdit.model,
        enginePower: this.truckToEdit.enginePower,
        fuel: this.truckToEdit.fuel,
        range: this.truckToEdit.range,
        segments: this.truckToEdit.segments
      });
    }
    this.loadSelects();
  }

  loadSelects = () => {
    this.fetchAllFuels();
    this.fetchAllRanges();
  }

  fetchAllFuels() {
    this.truckService.findAllFuels().subscribe(fuelsResponse => this.fuels = fuelsResponse);
  }

  fetchAllRanges() {
    this.truckService.findAllRanges().subscribe(rangesResponse => this.ranges = rangesResponse);
  }

  onSubmitForm() {
    this.truck.emit(this.truckForm.value);
  }

  segmentsSelected(segments: Segment[]) {
    this.truckForm.get('segments').setValue(segments);
  }

  get model() { return this.truckForm.get('model'); }
  get enginePower() { return this.truckForm.get('enginePower'); }
  get fuel() { return this.truckForm.get('fuel'); }
  get range() { return this.truckForm.get('range'); }
  get segments() { return this.truckForm.get('segments'); }
}
