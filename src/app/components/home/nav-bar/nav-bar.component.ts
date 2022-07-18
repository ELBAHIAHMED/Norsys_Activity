import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  navbarOpen = false;
  constructor() { }

  ngOnInit(): void {
  }
  setNavbarOpen() {
    this.navbarOpen = !this.navbarOpen;
  }
}
