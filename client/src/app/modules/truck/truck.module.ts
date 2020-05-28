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
import { TruckEditComponent } from './components/truck-edit/truck-edit.component';
import { TruckModalEditComponent } from './components/truck-edit/truck-modal-edit/truck-modal-edit.component';
import { SharedModule } from '../shared/shared.module';
import { TruckDeleteModalComponent } from './components/truck-delete-modal/truck-delete-modal.component';
import { TruckSearchComponent } from './components/truck-list/truck-search/truck-search.component';
import { TruckSearchFormComponent } from './components/truck-list/truck-search/truck-search-form/truck-search-form.component';


@NgModule({
  declarations: [
    TruckListComponent,
    TruckGridComponent,
    TruckCreateComponent,
    TruckCreateFormComponent,
    TruckCreateSegmentsComponent,
    TruckEditComponent,
    TruckModalEditComponent,
    TruckDeleteModalComponent,
    TruckSearchComponent,
    TruckSearchFormComponent
  ],
  imports: [
    CommonModule,
    TruckRoutingModule,
    NgSelectModule,
    ReactiveFormsModule,
    FormsModule,
    SharedModule
  ],
  exports: []
})
export class TruckModule { }
