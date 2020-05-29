import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AlertService } from './alert.service';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent implements OnInit, OnDestroy {

  alertSubscription: Subscription;
  message: string;
  type = 'info';

  constructor(private alertService: AlertService) { }

  ngOnInit(): void {
    this.alertSubscription = this.alertService.onAlerts().subscribe(alert => {
      this.message = alert.message;
      this.type = alert.type;
      setTimeout(() => {
        this.message = '';
      }, 5000);
    });
  }
  ngOnDestroy(): void {
    this.alertSubscription.unsubscribe();
  }
}
