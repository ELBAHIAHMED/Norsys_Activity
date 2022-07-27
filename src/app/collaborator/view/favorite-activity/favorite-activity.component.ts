import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Activity } from 'src/app/models/activity';

@Component({
  selector: 'app-favorite-activity',
  templateUrl: './favorite-activity.component.html',
})
export class FavoriteActivityComponent implements OnInit {
  favorite_activities: Activity[] | undefined;
  constructor(private titleService: Title) {
    this.titleService.setTitle('favorite-activity');
  }

  ngOnInit(): void {}
}
