import { Component, OnInit, Output, EventEmitter, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { TruckService } from 'src/app/services/truck.service';
import { Segment } from 'src/app/models/Segment';
import { Truck } from 'src/app/models/Truck';
import { Subscription } from 'rxjs';
import { TruckEventsService } from 'src/app/modules/shared/services/truck-events.service';

@Component({
  selector: 'app-truck-search-form',
  templateUrl: './truck-search-form.component.html',
  styleUrls: ['./truck-search-form.component.css']
})
export class TruckSearchFormComponent implements OnInit, OnDestroy {

  fuels: string[] = [];
  ranges: string[] = [];

  truckForm = new FormGroup({
    model: new FormControl(null, {
    }),
    enginePower: new FormControl(null, {
    }),
    fuel: new FormControl(null, {
    }),
    range: new FormControl(null, {
    }),
    segments: new FormControl(null)
  });

  @Output()
  truck = new EventEmitter<Truck>();

  searchFormSubscription: Subscription;

  constructor(private truckService: TruckService, private truckEventsService: TruckEventsService) { }

  ngOnInit(): void {
    this.loadSelects();
    this.searchFormSubscription = this.truckEventsService.searchTruckFormListener().subscribe(() => {
      this.truckForm.reset();
    });
  }

  ngOnDestroy() { this.searchFormSubscription.unsubscribe(); }

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

  onChangeValue() {
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
