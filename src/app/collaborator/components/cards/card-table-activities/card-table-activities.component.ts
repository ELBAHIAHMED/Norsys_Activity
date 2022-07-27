import { Component, Input, OnInit } from '@angular/core';
import { Activity } from 'src/app/models/activity';
import { ValueService } from 'src/app/services/values.service';

@Component({
  selector: 'app-card-table-activities',
  templateUrl: './card-table-activities.component.html',
})
export class CardTableActivitiesComponent implements OnInit {
  @Input()
  activities: Activity[] | undefined
  get color(): string {
    return this._color;
  }
  set color(color: string) {
    this._color = color !== "light" && color !== "dark" ? "light" : color;
  }
  private _color = "light";
  constructor() { }

  ngOnInit(): void {
  }

}
