import { Component, OnInit } from "@angular/core";
import { Title } from "@angular/platform-browser";
import { Collaborator } from "src/app/models/collaborator";
import { CollaboratorService } from "src/app/services/collaborator.service";
import { ValueService } from "src/app/services/values.service";


@Component({
  selector: "app-settings",
  templateUrl: "./settings.component.html",
})
export class SettingsComponent implements OnInit {
  collaborator: Collaborator | undefined;
  constructor(private titleService: Title, private collaboratorService: CollaboratorService, private valueService: ValueService) {
    this.titleService.setTitle("settings");
  }

  ngOnInit(): void {
    this.collaboratorService.getOneCollaborator(1).subscribe({
      next: (collaborator) => {
        console.log(collaborator);
        this.collaborator = collaborator;
      },
      error: (err) => {
        console.log('err All : ' + err);
      },
    });
  }
}
