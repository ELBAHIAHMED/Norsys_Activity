import { Component, OnInit } from "@angular/core";
import { ValueService } from "src/app/services/values.service";

@Component({
  selector: "app-card-profile",
  templateUrl: "./card-profile.component.html",
})
export class CardProfileComponent implements OnInit {
  constructor(public valueService: ValueService) {}

  ngOnInit(): void {
  }
}
