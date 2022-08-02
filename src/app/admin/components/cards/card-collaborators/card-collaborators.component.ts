import { Component, Input, OnInit } from '@angular/core';
import { Collaborator } from 'src/app/models/collaborator';

@Component({
  selector: 'app-card-collaborators',
  templateUrl: './card-collaborators.component.html',
  styles: [
  ]
})
export class CardCollaboratorsComponent implements OnInit {
  @Input()
  collaborators: Collaborator[] | undefined;
  constructor() { }

  ngOnInit(): void {
  }

}
