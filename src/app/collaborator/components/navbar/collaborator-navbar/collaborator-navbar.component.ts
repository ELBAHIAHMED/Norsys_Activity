import { Component, OnInit } from "@angular/core";
import { Collaborator } from "src/app/models/collaborator";
import { CollaboratorService } from "src/app/services/collaborator.service";

@Component({
  selector: "app-collaborator-navbar",
  templateUrl: "./collaborator-navbar.component.html",
})
export class CollaboratorNavbarComponent implements OnInit {
  collaborator: Collaborator | undefined;
  constructor(public collaboratorService:CollaboratorService) {}

  ngOnInit(): void {
    this.collaboratorService.getOneCollaborator(1).subscribe({
      next: (coll: Collaborator) => {
        this.collaborator = coll;
      },
      error: (err) => {
        console.log(err);
      }
    })
  }
}
