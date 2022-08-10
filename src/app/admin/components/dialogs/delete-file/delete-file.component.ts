import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-delete-file',
  templateUrl: './delete-file.component.html',
})
export class DeleteFileComponent implements OnInit {

  public fName: string | undefined;
  public fIndex: any;

  constructor(private modalRef: MatDialogRef<DeleteFileComponent>) { }

  ngOnInit() {
  }

  confirm() {
    this.modalRef.close(this.fIndex);
  }
  cancel() {
    this.modalRef.close();
  }

}
