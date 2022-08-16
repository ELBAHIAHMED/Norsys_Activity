import { Component, Input, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { Survey } from 'src/app/models/survey';

@Component({
  selector: 'app-form-statistics',
  templateUrl: './form-statistics.component.html',
  styleUrls: ['./form-statistics.component.css'],
})
export class FormStatisticsComponent implements OnInit {
  @Input()
  survey!: Survey;
  
      
  constructor() {}
  ngOnInit(): void {
    
  }
}
