import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TruckRoutingModule } from './truck-routing.module';
import { TruckListComponent } from './components/truck-list/truck-list.component';
import { TruckGridComponent } from './components/truck-list/truck-grid/truck-grid.component';


@NgModule({
  declarations: [TruckListComponent, TruckGridComponent],
  imports: [
    CommonModule,
    TruckRoutingModule
  ],
  exports: []
})
export class TruckModule { }
