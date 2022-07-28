import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { CollaboratorService } from 'src/app/services/collaborator.service';
import { ValueService } from 'src/app/services/values.service';

@Component({
  selector: 'app-collaborators',
  templateUrl: './collaborators.component.html',
  styles: [
  ]
})
export class CollaboratorsComponent implements OnInit {

  listView = true;
  cardView = false;
  constructor(private titleService: Title, public valueService: ValueService, private collaboratorsService: CollaboratorService) {
    this.titleService.setTitle('collaborators');
  }

  ngOnInit(): void {
    this.collaboratorsService.getAllCollaborators().subscribe({
      next: (collaborators) => {
        console.log(collaborators);
        this.valueService.collaborators = collaborators;
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

}
