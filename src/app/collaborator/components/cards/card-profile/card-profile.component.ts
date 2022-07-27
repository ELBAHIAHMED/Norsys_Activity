import { Component, Input, OnInit } from "@angular/core";
import { Collaborator } from "src/app/models/collaborator";

@Component({
  selector: "app-card-profile",
  templateUrl: "./card-profile.component.html",
})
export class CardProfileComponent implements OnInit {
  @Input()
  collaborator: Collaborator | undefined;

  constructor() {}

  ngOnInit(): void {
  }
}
