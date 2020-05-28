import { Component, OnInit } from '@angular/core';
import { Truck } from 'src/app/models/Truck';
import { TruckService } from 'src/app/services/truck.service';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/modules/shared/alert/alert.service';

@Component({
  selector: 'app-truck-create',
  templateUrl: './truck-create.component.html',
  styleUrls: ['./truck-create.component.css']
})
export class TruckCreateComponent implements OnInit {

  constructor(private truckService: TruckService, private router: Router, private alertService: AlertService) { }

  ngOnInit(): void { }

  cancelAddNewTruck() {
    this.router.navigate(['/truck']);
  }

  mapTruckToSave(truck: Truck) {
    try {
      this.truckService.save(truck)
        .subscribe(response => {
          if (response.id) {
            this.alertService.alert('Congratulations. New Truck successfully created.', 'success');
            this.router.navigate(['/truck']);
          }
        });
    } catch (error) {
    }
  }

}
