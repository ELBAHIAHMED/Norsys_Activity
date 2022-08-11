import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Survey } from 'src/app/models/survey';
import { SurveyService } from 'src/app/services/survey.service';
import { ValueService } from 'src/app/services/values.service';

@Component({
  selector: 'app-formulaire',
  templateUrl: './formulaire.component.html',
  styleUrls: ['./formulaire.component.scss'],
})
export class FormulaireComponent implements OnInit {
  isAddSurvey = false;
  filterdSurveys!: Survey[];
  date: any;
  constructor(
    private title: Title,
    private surveyService: SurveyService,
    private valueService: ValueService
  ) {
    this.title.setTitle('formulaire');
  }

  ngOnInit(): void {
    this.surveyService.getAllSurveys().subscribe({
      next: (res) => {
        console.log(res);
        this.valueService.surveys = res;
        this.filterdSurveys = this.valueService.surveys;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  showAddSurvey() {
    this.isAddSurvey = true;
  }
  showSurvey() {
    this.isAddSurvey = false;
  }
  filterSurveysByDate(date: Date) {
    if (date) {
      this.valueService.surveys.filter((survey) => survey.Date === date);
      this.filterdSurveys = this.valueService.surveys.filter((survey) => {
        let survey_date = new Date(survey.Date);
        return (
          survey_date.getMonth() === date.getMonth() &&
          survey_date.getFullYear() === date.getFullYear() &&
          survey_date.getDate() === date.getDate()
        );
      });
    }
  }
  clearDate() {
    this.date = null;
    this.filterdSurveys = this.valueService.surveys;
  }
}
