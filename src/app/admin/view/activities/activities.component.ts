import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Activity } from 'src/app/models/activity';
import { ActivityService } from 'src/app/services/activity.service';
import { ValueService } from 'src/app/services/values.service';

@Component({
  selector: 'app-tables',
  templateUrl: './activities.component.html',
})
export class ActivitiesComponent implements OnInit {
  listView = true;
  cardView = false;
  innerWidth: any;
  step = 0;
  addActivityShown = false;
  activity: Activity | undefined;
  constructor(
    private titleService: Title,
    public valueService: ValueService,
    private activityService: ActivityService
  ) {
    this.titleService.setTitle('activities');
  }

  ngOnInit(): void {
    this.innerWidth = window.innerWidth;
    this.activityService.getAllActivites().subscribe({
      next: (activities) => {
        console.log(activities);
        this.valueService.activities = activities;
      },
      error: (err) => {
        console.log(err);
      },
    });
    if(this.innerWidth<=768) {
      this.cardView = true;
      this.listView = false;
    }
  }
  toggleToListView() {
    this.listView = true;
    this.cardView = false;
  }

  toggleToCardView() {
    this.listView = false;
    this.cardView = true;
  }
  showOrHideAddActivity() {
    this.step = 0;
    this.addActivityShown = !this.addActivityShown;
  }

  addActivity(addActivityForm: NgForm) {
    this.step = 0;
    this.showOrHideAddActivity();
    if (addActivityForm.valid) {
      console.log(addActivityForm.value);
    } else console.log('error');
  }
  next() {
    this.step++;
  }
}
