import { Component, OnInit } from '@angular/core';
import { ValueService } from 'src/app/services/values.service';

@Component({
  selector: 'app-card-table-activities',
  templateUrl: './card-table-activities.component.html',
})
export class CardTableActivitiesComponent implements OnInit {
  get color(): string {
    return this._color;
  }
  set color(color: string) {
    this._color = color !== "light" && color !== "dark" ? "light" : color;
  }
  private _color = "dark";
  constructor(public valueService: ValueService) { }

  ngOnInit(): void {
  }

}
