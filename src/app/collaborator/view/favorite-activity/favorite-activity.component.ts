import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Activity } from 'src/app/models/activity';

@Component({
  selector: 'app-favorite-activity',
  templateUrl: './favorite-activity.component.html',
})
export class FavoriteActivityComponent implements OnInit {
  favorite_activities: Activity[] | undefined;
  listView = true;
  cardView = false;
  constructor(private titleService: Title) {
    this.titleService.setTitle('favorite-activity');
  }

  ngOnInit(): void {}
  toggleToListView() {
    this.listView = true;
    this.cardView = false;
  }

  toggleToCardView() {
    this.listView = false;
    this.cardView = true;
  }
}
