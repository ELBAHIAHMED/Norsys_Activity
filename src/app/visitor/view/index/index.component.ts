import { Component, OnInit } from "@angular/core";
import { Title } from "@angular/platform-browser";

@Component({
  selector: "app-index",
  templateUrl: "./index.component.html",
})
export class IndexComponent implements OnInit {
  constructor(private titleService: Title) {
    this.titleService.setTitle("Norsys-activity");
  }

  ngOnInit(): void {}
}
