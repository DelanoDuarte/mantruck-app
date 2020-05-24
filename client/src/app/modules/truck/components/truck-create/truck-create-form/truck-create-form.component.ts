import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Truck } from 'src/app/models/Truck';
import { TruckService } from 'src/app/services/truck.service';

@Component({
  selector: 'app-truck-create-form',
  templateUrl: './truck-create-form.component.html',
  styleUrls: ['./truck-create-form.component.css']
})
export class TruckCreateFormComponent implements OnInit {

  fuels: string[] = [];

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
    segments: new FormControl([])
  });

  @Output()
  truck = new EventEmitter<Truck>();

  constructor(private truckService: TruckService) { }

  ngOnInit(): void {
    this.fetchAllFuels();
  }

  fetchAllFuels() {
    this.truckService.findAllFuels().subscribe(fuelsResponse => this.fuels = fuelsResponse);
  }

  onSubmitForm() {
    this.truck.emit(this.truckForm.value);
  }

  get model() { return this.truckForm.get('model'); }
  get enginePower() { return this.truckForm.get('enginePower'); }
  get fuel() { return this.truckForm.get('fuel'); }
}
