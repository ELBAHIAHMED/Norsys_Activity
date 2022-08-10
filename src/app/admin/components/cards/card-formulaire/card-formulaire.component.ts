import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Survey } from 'src/app/models/survey';
import { SurveyService } from 'src/app/services/survey.service';
import { ValueService } from 'src/app/services/values.service';
import { ConfirmationDialog } from '../../dialogs/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-card-formulaire',
  templateUrl: './card-formulaire.component.html',
  styleUrls: ['./card-formulaire.component.scss'],
})
export class CardFormulaireComponent implements OnInit {
  panelOpenState = false;
  @Input()
  filterdSurveys!: Survey[];
  constructor(
    public valueService: ValueService,
    private dialog_delete_form: MatDialog,
    private surveyService: SurveyService
  ) {}
  deleteSurvey(survey: Survey) {
    this.dialog_delete_form
      .open(ConfirmationDialog, {
        width: '450px',
        data:{title: 'Supprimer le formulaire', body: 'Voulez-vous supprimez ce formulaire ?'},
        disableClose: true,
      })
      .afterClosed()
      .subscribe((res) => {
        console.log(res);
        if (res) {
          this.valueService.surveys.splice(
            this.valueService.surveys.indexOf(survey),
            1
          );
          this.surveyService.deleteOneSurvey(survey.id).subscribe({
            next: (res) => {
              console.log(res);
            },
            error: (err) => {
              console.log(err);
            },
          });
        }
      });
  }
  editForm() {}
  ngOnInit(): void {}
}
