import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'client';

  constructor(private router: Router) {
  }

  ngOnInit() { }

  returnMenuList(): any[] {
    return [
      { path: '/truck', active: this.isPathActivated('/truck'), label: 'Truck', icon: 'truck' }
    ];
  }

  isPathActivated(path: string): boolean {
    return path === this.router.url;
  }
}
