import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TruckListComponent } from './components/truck-list/truck-list.component';
import { TruckCreateComponent } from './components/truck-create/truck-create.component';


const routes: Routes = [
  { path: '', component: TruckListComponent },
  { path: 'new', component: TruckCreateComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TruckRoutingModule { }
