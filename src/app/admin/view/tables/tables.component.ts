import { Component, OnInit } from "@angular/core";
import { Title } from "@angular/platform-browser";

@Component({
  selector: "app-tables",
  templateUrl: "./tables.component.html",
})
export class TablesComponent implements OnInit {
  constructor(private titleService: Title) {
    this.titleService.setTitle("tables");
  }

  ngOnInit(): void {}
}
