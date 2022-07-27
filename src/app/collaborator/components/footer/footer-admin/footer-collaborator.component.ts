import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-footer-collaborator",
  templateUrl: "./footer-collaborator.component.html",
})
export class FooterCollaboratorComponent implements OnInit {
  date = new Date().getFullYear();
  constructor() {}

  ngOnInit(): void {}
}
