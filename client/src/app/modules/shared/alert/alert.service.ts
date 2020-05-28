import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Alert } from './alert';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  private alerts = new Subject<Alert>();

  constructor() { }

  alert(message: string, type?: string): void {
    this.alerts.next(new Alert(message, type));
  }

  onAlerts(): Observable<Alert> {
    return this.alerts.asObservable();
  }
}
