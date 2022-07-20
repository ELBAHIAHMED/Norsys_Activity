import { Component, OnInit } from "@angular/core";
import { Title } from "@angular/platform-browser";

@Component({
  selector: "app-maps",
  templateUrl: "./maps.component.html",
})
export class MapsComponent implements OnInit {
  constructor(private titleService: Title) {
    this.titleService.setTitle("maps");
  }

  ngOnInit(): void {}
}
