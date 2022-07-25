import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivityService } from 'src/app/services/activity.service';
import { ValueService } from 'src/app/services/values.service';
import { AdminService } from '../../../services/admin.service';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
})
export class TablesComponent implements OnInit {
  constructor(private titleService: Title,private adminService: AdminService, private valueService: ValueService, private activityService: ActivityService) {
    this.titleService.setTitle('tables');
  }

  ngOnInit(): void {
    this.adminService.getAllCollaborators().subscribe({
      next: (collaborators) => {
        console.log(collaborators);
        this.valueService.collaborators = collaborators;
      },
      error: (err) => {
        console.log("err All : "+err);
      },
    });
    
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
  
}
