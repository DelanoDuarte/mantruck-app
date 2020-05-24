import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TruckListComponent } from './components/truck-list/truck-list.component';


const routes: Routes = [
  { path: '', component: TruckListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TruckRoutingModule { }
