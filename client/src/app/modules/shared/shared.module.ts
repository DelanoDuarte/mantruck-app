import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { AlertComponent } from './alert/alert.component';
import { TruckEventsService } from './services/truck-events.service';

@NgModule({
  declarations: [AlertComponent],
  imports: [
    CommonModule,
    NgbAlertModule
  ],
  providers: [TruckEventsService],
  exports: [
    AlertComponent,
    NgbAlertModule
  ]
})
export class SharedModule { }
