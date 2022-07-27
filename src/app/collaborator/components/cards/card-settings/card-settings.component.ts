import { Component, Input, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Collaborator } from 'src/app/models/collaborator';
import { ValueService } from 'src/app/services/values.service';

@Component({
  selector: 'app-card-settings',
  templateUrl: './card-settings.component.html',
})
export class CardSettingsComponent implements OnInit {
  @Input()
  collaborator: Collaborator | undefined;
  constructor() {}
  ngOnInit(): void {}
  updateCollaborator(updateCollaboratorForm: NgForm) {
    if (updateCollaboratorForm.valid) {
      console.log(updateCollaboratorForm.value);
    } else {
      console.log('not Valid');
    }
  }
}
