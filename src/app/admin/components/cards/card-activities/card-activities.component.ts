import { Component, Input, OnInit } from '@angular/core';
import { Activity } from 'src/app/models/activity';

@Component({
  selector: 'app-card-activities',
  templateUrl: './card-activities.component.html',
  styles: [
  ]
})
export class CardActivitiesComponent implements OnInit {
  @Input()
  activities: Activity[] | undefined;
  constructor() { }

  ngOnInit(): void {
  }
 
}
