import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnInit {

  constructor(private titleService: Title) {
    this.titleService.setTitle("dashboard");
   }

  ngOnInit(): void {
  }

}
