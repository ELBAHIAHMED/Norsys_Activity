import { Component, OnInit, Input } from "@angular/core";
import { ValueService } from "src/app/services/values.service";

@Component({
  selector: "app-card-table-collaborator",
  templateUrl: "./card-table-collaborator.component.html",
})
export class CardTableCollaboratorComponent implements OnInit {
  @Input()
  get color(): string {
    return this._color;
  }
  set color(color: string) {
    this._color = color !== "light" && color !== "dark" ? "light" : color;
  }
  private _color = "dark";
  constructor(public valueService: ValueService) {}

  ngOnInit(): void {}
}
