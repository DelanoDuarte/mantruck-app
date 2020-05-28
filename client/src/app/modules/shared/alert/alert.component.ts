import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { AlertService } from './alert.service';
import { debounceTime } from 'rxjs/operators';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent implements OnInit, OnDestroy {

  alertSubscription: Subscription;
  message: string;
  type: string;

  constructor(private alertService: AlertService) { }

  ngOnInit(): void {
    this.alertSubscription = this.alertService.onAlerts().subscribe(alert => {
      this.message = alert.message;
      this.type = alert.type;

      this.alertService.onAlerts().pipe(
        debounceTime(5000)
      ).subscribe(() => this.message = '');
    });
  }
  ngOnDestroy(): void {
    this.alertSubscription.unsubscribe();
  }
}
