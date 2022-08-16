import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DeleteFileComponent } from '../dialogs/delete-file/delete-file.component';
import { File } from 'src/app/models/file';

@Component({
  selector: 'app-form-upload',
  templateUrl: './form-upload.component.html',
  styleUrls: ['./form-upload.component.scss'],
})
export class FormUploadComponent implements OnInit {
  //@Output() uploadFileEvent = new EventEmitter<any>();
  @Input() surveyFiles!: File[]
  //surveyFiles: any[] = [];
  noFileUploaded = true;
  constructor(private _snackBar: MatSnackBar, public dialog: MatDialog) {}
  ngOnInit(): void {
    if(this.surveyFiles.length > 0) {
      console.log(this.surveyFiles.length);
      
      this.noFileUploaded = false;
    }
  }

  onFileChange(event: any) {
    let files = event.target!.files;
    console.log(files[0]);
    
    if (files !== undefined) {
      this.noFileUploaded = false;
      let _File = new File(
        files[0].lastModified,
        files[0].lastModifiedDate,
        files[0].name,
        files[0].size,
        files[0].type
      );
      this.surveyFiles.push(_File);
      console.log(this.surveyFiles.length);
      //this.uploadFileEvent.emit(this.surveyFiles);
    }
    
    this._snackBar.open('Successfully upload!', 'Close', {
      duration: 2000,
    });
  }

  deleteFile(f: any) {
    this.surveyFiles = this.surveyFiles!.filter(function (w: any) {
      return w.name != f.name;
    });
    this._snackBar.open('Successfully delete!', 'Close', {
      duration: 2000,
    });
  }

  openConfirmDialog(pIndex: any): void {
    const dialogRef = this.dialog.open(DeleteFileComponent, {
      panelClass: 'modal-xs',
    });
    dialogRef.componentInstance.fName = this.surveyFiles![pIndex].name;
    dialogRef.componentInstance.fIndex = pIndex;

    dialogRef.afterClosed().subscribe((result) => {
      if (result !== undefined) {
        this.deleteFromArray(result);
      }
    });
  }

  deleteFromArray(index: any) {
    console.log(this.surveyFiles);
    this.surveyFiles!.splice(index, 1);
    if (this.surveyFiles!.length == 0) {
      this.noFileUploaded = true;
    }
  }
  onFileDroped($event:any) {
    this.noFileUploaded = false;
    for (const item of $event) {
      let _File = new File(
        item.lastModified,
        item.lastModifiedDate,
        item.name,
        item.size,
        item.type
      );
      console.log(_File);
      this.surveyFiles.push(_File);
    }
    console.log(this.surveyFiles);
    //this.uploadFileEvent.emit(this.surveyFiles);
  }
}
