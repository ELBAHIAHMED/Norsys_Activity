import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivityService } from 'src/app/services/activity.service';
import { ValueService } from 'src/app/services/values.service';

@Component({
  selector: 'app-tables',
  templateUrl: './activities.component.html',
})
export class ActivitiesComponent implements OnInit {
  listView = true;
  cardView = false;
  addActivityShown = false;
  constructor(private titleService: Title, public valueService: ValueService, private activityService: ActivityService) {
    this.titleService.setTitle('activities');
  }

  ngOnInit(): void {
    this.activityService.getAllActivites().subscribe({
      next: (activities) => {
        console.log(activities);
        this.valueService.activities = activities;
      },
      error: (err) => {
        console.log("err All : "+err);
      },
    });
  }
  toggleToListView() {
    this.listView = true;
    this.cardView = false;
  }

  toggleToCardView() {
    this.listView = false;
    this.cardView = true;
  }
  showAddActivity() {
    this.addActivityShown = !this.addActivityShown;
  }
}
