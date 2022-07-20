import { Component, OnInit } from "@angular/core";
import { Title } from "@angular/platform-browser";

@Component({
  selector: "app-settings",
  templateUrl: "./settings.component.html",
})
export class SettingsComponent implements OnInit {
  constructor(private titleService: Title) {
    this.titleService.setTitle("setting");
  }

  ngOnInit(): void {}
}
