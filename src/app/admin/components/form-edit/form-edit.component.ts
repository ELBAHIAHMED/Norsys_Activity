import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Question } from 'src/app/models/question';
import { QuestionType } from 'src/app/models/question-type';
import { Survey } from 'src/app/models/survey';
import { SurveyService } from 'src/app/services/survey.service';
import { ValueService } from 'src/app/services/values.service';
import { ConfirmationDialog } from '../dialogs/confirmation-dialog/confirmation-dialog.component';
import { File } from 'src/app/models/file';
import { Option } from 'src/app/models/option';
@Component({
  selector: 'app-form-edit',
  templateUrl: './form-edit.component.html',
  styleUrls: ['./form-edit.component.scss'],
})
export class FormEditComponent implements OnInit {
  @Input()
  survey!: Survey;
  surveyQuestions!: FormArray;
  surveyForm!: FormGroup;
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
    private valueService: ValueService
  ) {}

  ngOnInit() {
    this.initForm();
    this.surveyQuestionsArray = (
      this.surveyForm.get('surveyQuestions') as FormArray
    ).controls;
  }
  private initForm() {
    let surveyTitle = this.survey.Title;
    let surveyUrl = this.survey.Url;
    let surveyDescription = this.survey.Description;
    this.surveyQuestions = new FormArray([]);

    this.surveyForm = new FormGroup({
      surveyTitle: new FormControl(surveyTitle, [Validators.required]),
      surveyDescription: new FormControl(surveyDescription),
      surveyUrl: new FormControl(surveyUrl),
      surveyQuestions: this.surveyQuestions,
    });

    console.log(this.survey.Question);

    this.survey.Question.forEach((question: Question) => {
      let surveyQuestionItem = new FormGroup({
        questionTitle: new FormControl(question.Text),
        questionType: new FormControl(question.Type),
        questionGroup: new FormGroup({}),
      });
      (<FormArray>this.surveyForm.get('surveyQuestions')).push(
        surveyQuestionItem
      );
      //here
      console.log(question.Type);
      let index = this.survey.Question.indexOf(question);
      this.selectedOption.push(question.Type);

      let options = new FormArray([]);
      let showRemarksBox = new FormControl(false);
      this.addOptionControls(
        question.Type,
        this.survey.Question.indexOf(question)
      );
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

      question.options.forEach((option: Option) => {
        console.log(option);
        let arrayForm = this.surveyForm.get('surveyQuestions') as FormArray;
        let groupForm = arrayForm.at(index).get('questionGroup') as FormGroup;
        const optionGroup = new FormGroup({
          optionText: new FormControl(option.OptionText, Validators.required),
        });
        (<FormArray>groupForm.get('options')).push(optionGroup);
      });
      this.surveyQuestionsArray = (
        this.surveyForm.get('surveyQuestions') as FormArray
      ).controls;
      console.log(question.Text);
    });

    this.surveyQuestionsArray = (
      this.surveyForm.get('surveyQuestions') as FormArray
    ).controls;
  }

  onAddQuestion() {
    console.log(this.surveyForm);

    const surveyQuestionItem = new FormGroup({
      questionTitle: new FormControl(''),
      questionType: new FormControl(''),
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
    console.log(itemIndex);

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

  updateSurvey() {
    let formData = this.surveyForm.value;
    console.log(formData);

    console.log();
    this.survey.Description = formData.surveyDescription;
    this.survey.Url = formData.surveyUrl;
    this.survey.Title = formData.surveyTitle;
    let Questions: Question[] = [];
    console.log('heeeeeere-----' + this.survey.Files);
    let Files: any[] = [];

    let surveyQuestions = formData.surveyQuestions;

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

        Questions.push(questionItem);
        this.surveyQuestionsArray = (
          this.surveyForm.get('surveyQuestions') as FormArray
        ).controls;
      }
    );
    this.survey.Question = Questions;
    console.log(this.survey);
    this.surveyService.updateSurvey(this.survey, this.survey.id).subscribe({
      next: (res) => {
        console.log(res);
      },
      error: (err) => {
        console.log(err);
      },
    });
    console.log('updating survey');
  }

  onSubmit() {
    this.updateSurvey();
    //window.location.reload();
  }
}
