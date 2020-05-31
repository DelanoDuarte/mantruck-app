import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';
import { SharedModule } from '../shared/shared.module';
import { TruckCreateFormComponent } from './components/truck-create/truck-create-form/truck-create-form.component';
import { TruckCreateSegmentsComponent } from './components/truck-create/truck-create-segments/truck-create-segments.component';
import { TruckCreateComponent } from './components/truck-create/truck-create.component';
import { TruckDeleteModalComponent } from './components/truck-delete-modal/truck-delete-modal.component';
import { TruckGridComponent } from './components/truck-list/truck-grid/truck-grid.component';
import { TruckListComponent } from './components/truck-list/truck-list.component';
import { TruckSearchFormComponent } from './components/truck-list/truck-search/truck-search-form/truck-search-form.component';
import { TruckSearchComponent } from './components/truck-list/truck-search/truck-search.component';
import { TruckModalEditComponent } from './components/truck-modal-edit/truck-modal-edit.component';
import { TruckRoutingModule } from './truck-routing.module';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';



@NgModule({
  declarations: [
    TruckListComponent,
    TruckGridComponent,
    TruckCreateComponent,
    TruckCreateFormComponent,
    TruckCreateSegmentsComponent,
    TruckModalEditComponent,
    TruckDeleteModalComponent,
    TruckSearchComponent,
    TruckSearchFormComponent
  ],
  imports: [
    CommonModule,
    TruckRoutingModule,
    NgSelectModule,
    NgbPaginationModule,
    ReactiveFormsModule,
    FormsModule,
    SharedModule
  ],
  exports: []
})
export class TruckModule { }
