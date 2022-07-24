import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { AdminService } from '../../../services/admin.service';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
})
export class TablesComponent implements OnInit {
  constructor(private titleService: Title,private adminService: AdminService) {
    this.titleService.setTitle('tables');
  }

  ngOnInit(): void {}
  getAllCollaborators() {
    this.adminService.getAllCollaborators().subscribe({
      next: (collaborators) => {
        console.log(collaborators);
      },
      error: (err) => {
        console.log("err All : "+err);
      },
    });
  }
  
}
