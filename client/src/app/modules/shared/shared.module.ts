import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TruckEventsService } from './services/truck-events.service';

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers: [TruckEventsService]
})
export class SharedModule { }
