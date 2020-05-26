import { Component, AfterViewInit } from '@angular/core';
import * as Feather from 'feather-icons';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {
  title = 'client';

  constructor(private router: Router) {
  }

  ngAfterViewInit() {
    Feather.replace();
  }

  returnMenuList(): any[] {
    return [
      { path: '/home', active: this.isPathActivated('/home'), label: 'Home', icon: 'home' },
      { path: '/truck', active: this.isPathActivated('/truck'), label: 'Truck', icon: 'truck' }
    ];
  }

  isPathActivated(path: string): boolean {
    return path === this.router.url;
  }
}
