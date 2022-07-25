import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AdminService } from 'src/app/services/admin.service';
import { ValueService } from 'src/app/services/values.service';

@Component({
  selector: 'app-card-settings',
  templateUrl: './card-settings.component.html',
})
export class CardSettingsComponent implements OnInit {
  constructor(
    public valueService: ValueService,
    private adminService: AdminService
  ) {}
  ngOnInit(): void {}
  updateAdmin(updateAdminForm: NgForm) {
    if (updateAdminForm.valid) {
      this.adminService.UpdateAdmin(updateAdminForm.value).subscribe({
        next: (admin) => {
          console.log(admin);
        },
        error: (err) => {
          console.log('err All : ' + err);
        },
      });
      console.log(updateAdminForm.value);
    } else {
      console.log('not Valid');
    }
  }
}
