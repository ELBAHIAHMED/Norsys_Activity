import { Component, OnInit } from "@angular/core";
import { Title } from "@angular/platform-browser";
import { AdminService } from 'src/app/services/admin.service';
import { ValueService } from "src/app/services/values.service";


@Component({
  selector: "app-settings",
  templateUrl: "./settings.component.html",
})
export class SettingsComponent implements OnInit {
  constructor(private titleService: Title, private adminService: AdminService, private valueService: ValueService) {
    this.titleService.setTitle("setting");
  }

  ngOnInit(): void {
    this.adminService.getAdmin().subscribe({
      next: (admin) => {
        console.log(admin);
        this.valueService.admin = admin;
      },
      error: (err) => {
        console.log('err All : ' + err);
      },
    });
  }
}
