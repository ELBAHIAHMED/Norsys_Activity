import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Question } from 'src/app/models/question';
import { Survey } from 'src/app/models/survey';
import { Option } from 'src/app/models/option';

import { SurveyService } from 'src/app/services/survey.service';
import { ValueService } from 'src/app/services/values.service';
import { ConfirmationDialog } from '../dialogs/confirmation-dialog/confirmation-dialog.component';
import { File } from 'src/app/models/file';
import { Route, Router } from '@angular/router';
import { QuestionType } from 'src/app/models/question-type';


@Component({
  selector: 'app-survey',
  templateUrl: './survey.component.html',
  styleUrls: ['./survey.component.scss'],
})
export class SurveyComponent implements OnInit {
  surveyQuestions!: FormArray;
  surveyForm!: FormGroup;
  surveyFiles!: any[];
  selectedOption: any = [];

  surveyQuestionsArray: any;

  questions: QuestionType[] = [
    { value: 'Seul choix', viewValue: 'Seul choix' },
    { value: 'Multi choix', viewValue: 'Multi choix' },
    { value: 'Texte', viewValue: 'Texte' },
  ];

  constructor(
    private dialog_delete_question: MatDialog,
    private surveyService: SurveyService,
    private valueService: ValueService,
  ) {}

  ngOnInit() {
    this.initForm();
    this.surveyQuestionsArray = (
      this.surveyForm.get('surveyQuestions') as FormArray
    ).controls;
  }
  getFiles(surveyFiles: any) {
    this.surveyFiles = surveyFiles;
    console.log(this.surveyFiles);
  }
  private initForm() {
    let surveyTitle = '';
    let surveyUrl = '';
    let surveyDescription = '';
    this.surveyFiles = [];
    this.surveyQuestions = new FormArray([]);

    this.surveyForm = new FormGroup({
      surveyTitle: new FormControl(surveyTitle, [Validators.required]),
      surveyDescription: new FormControl(surveyDescription),
      surveyUrl: new FormControl(surveyUrl),
      surveyQuestions: this.surveyQuestions,
    });

    this.onAddQuestion();
  }

  onAddQuestion() {
    console.log(this.surveyForm);

    const surveyQuestionItem = new FormGroup({
      questionTitle: new FormControl('', Validators.required),
      questionType: new FormControl('', Validators.required),
      questionGroup: new FormGroup({}),
    });

    (<FormArray>this.surveyForm.get('surveyQuestions')).push(
      surveyQuestionItem
    );
    this.surveyQuestionsArray = (
      this.surveyForm.get('surveyQuestions') as FormArray
    ).controls;
  }
  openDeleteQuestionDialog(index: number): void {
    this.dialog_delete_question
      .open(ConfirmationDialog, {
        width: '450px',
        data: {
          title: 'Supprimer la question',
          body: 'Voulez-vous supprimez cette question ?',
        },
        disableClose: true,
      })
      .afterClosed()
      .subscribe((res) => {
        console.log(res);
        if (res) {
          this.onRemoveQuestion(index);
        }
      });
  }

  onRemoveQuestion(index: number) {
    let arrayForm = this.surveyForm.get('surveyQuestions') as FormArray;
    let groupForm = arrayForm.at(index).get('questionGroup') as FormGroup;
    let typeForm = arrayForm.at(index).get('questionType') as FormControl;
    groupForm = new FormGroup({});
    typeForm = new FormControl({});
    //this.surveyForm.controls.surveyQuestions['controls'][index].controls.questionGroup = new FormGroup({});
    //this.surveyForm.controls.surveyQuestions['controls'][index].controls.questionType = new FormControl({});

    (<FormArray>this.surveyForm.get('surveyQuestions')).removeAt(index);
    this.selectedOption.splice(index, 1);

    this.surveyQuestionsArray = (
      this.surveyForm.get('surveyQuestions') as FormArray
    ).controls;
    console.log(this.surveyForm);
  }

  onSeletQuestionType(questionType: string, index: any) {
    if (questionType === 'Seul choix' || questionType === 'Multi choix') {
      this.addOptionControls(questionType, index);
    }
  }

  addOptionControls(questionType: string, index: number) {
    let options = new FormArray([]);
    let showRemarksBox = new FormControl(false);
    (
      (this.surveyForm.get('surveyQuestions') as FormArray)
        .at(index)
        .get('questionGroup') as FormGroup
    ).addControl('options', options);
    (
      (this.surveyForm.get('surveyQuestions') as FormArray)
        .at(index)
        .get('questionGroup') as FormGroup
    ).addControl('options', options);

    this.clearFormArray(
      (this.surveyForm.get('surveyQuestions') as FormArray)
        .at(index)
        .get('questionGroup')
        ?.get('options') as FormArray
    );

    this.addOption(index);
    this.addOption(index);
    this.surveyQuestionsArray = (
      this.surveyForm.get('surveyQuestions') as FormArray
    ).controls;
  }

  private clearFormArray(formArray: FormArray) {
    while (formArray.length !== 0) {
      formArray.removeAt(0);
    }
  }

  addOption(index: number) {
    let arrayForm = this.surveyForm.get('surveyQuestions') as FormArray;
    let groupForm = arrayForm.at(index).get('questionGroup') as FormGroup;
    const optionGroup = new FormGroup({
      optionText: new FormControl('', Validators.required),
    });
    (<FormArray>groupForm.get('options')).push(optionGroup);
  }

  removeOption(questionIndex: number, itemIndex: number) {
    (
      (this.surveyForm.get('surveyQuestions') as FormArray)
        .at(questionIndex)
        .get('questionGroup')
        ?.get('options') as FormArray
    ).removeAt(itemIndex);
    this.surveyQuestionsArray = (
      this.surveyForm.get('surveyQuestions') as FormArray
    ).controls;
  }

  postSurvey() {
    let formData = this.surveyForm.value;
    console.log(formData);

    console.log();
    let ID = this.valueService.surveys.length;
    let Description = formData.surveyDescription;
    let Url = formData.surveyUrl;
    let Title = formData.surveyTitle;
    let IsDeleted = false;
    let IsAvailable = false;
    let Questions: Question[] = [];
    console.log('heeeeeere-----' + this.surveyFiles);
    let Files: any[] = [];
    this.surveyFiles.forEach((file) => {
      console.log(file.name);
      let _File = new File(
        file.lastModified,
        file.lastModifiedDate,
        file.name,
        file.size,
        file.type
      );
      Files.push(_File);
    });

    let surveyQuestions = formData.surveyQuestions;
    let survey = new Survey(
      ID,
      Description,
      Url,
      Title,
      IsDeleted,
      IsAvailable,
      Questions,
      new Date(),
      Files
    );

    surveyQuestions.forEach(
      (
        question: {
          questionType: any;
          questionTitle: any;
          questionGroup: {
            hasOwnProperty: (arg0: string) => any;
            showRemarksBox: boolean;
            options: any[];
          };
        },
        index: any,
        array: any
      ) => {
        let questionItem = {
          id: 0,
          Type: question.questionType,
          Text: question.questionTitle,
          options: [] as Option[],
          Required: false,
          Remarks: '',
          hasRemarks: false,
        };
        if (question.questionGroup.hasOwnProperty('showRemarksBox')) {
          questionItem.hasRemarks = question.questionGroup.showRemarksBox;
        }

        if (question.questionGroup.hasOwnProperty('options')) {
          question.questionGroup.options.forEach((option) => {
            let optionItem: Option = {
              id: 0,
              OptionText: option.optionText,
              OptionColor: '',
              hasRemarks: false,
            };
            questionItem.options.push(optionItem);
          });
        }

        survey.Question.push(questionItem);
        this.surveyQuestionsArray = (
          this.surveyForm.get('surveyQuestions') as FormArray
        ).controls;
      }
    );

    console.log(survey);
    // if (survey.Title !== '' && survey.Question.length > 0) {
      this.surveyService.addSurvey(survey).subscribe({
        next: (res) => {
          console.log(res);
          this.valueService.surveys.push(survey);
        },
        error: (err) => {
          console.log(err);
        },
      });
      console.log('posting survey');
    // }
  }

  onSubmit() {
    this.postSurvey();
    //window.location.reload();
  }
}
