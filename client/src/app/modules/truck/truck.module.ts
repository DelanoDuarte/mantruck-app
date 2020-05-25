import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TruckRoutingModule } from './truck-routing.module';
import { TruckListComponent } from './components/truck-list/truck-list.component';
import { TruckGridComponent } from './components/truck-list/truck-grid/truck-grid.component';
import { TruckCreateComponent } from './components/truck-create/truck-create.component';
import { TruckCreateFormComponent } from './components/truck-create/truck-create-form/truck-create-form.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { TruckCreateSegmentsComponent } from './components/truck-create/truck-create-segments/truck-create-segments.component';
import { NgSelectModule } from '@ng-select/ng-select';


@NgModule({
  declarations: [TruckListComponent, TruckGridComponent, TruckCreateComponent, TruckCreateFormComponent, TruckCreateSegmentsComponent],
  imports: [
    CommonModule,
    TruckRoutingModule,
    NgSelectModule,
    ReactiveFormsModule,
    FormsModule
  ],
  exports: []
})
export class TruckModule { }
