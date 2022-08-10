import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DeleteFileComponent } from '../dialogs/delete-file/delete-file.component';

@Component({
  selector: 'app-form-upload',
  templateUrl: './form-upload.component.html',
  styleUrls: ['./form-upload.component.scss']
})
export class FormUploadComponent implements OnInit {
  @Output() uploadFileEvent = new EventEmitter<any>();
  surveyFiles:any;
  noFileUploaded = true;  
  constructor(private _snackBar: MatSnackBar, public dialog: MatDialog){}
  ngOnInit(): void {
  
  }

  onFileChange(pFileList: any){
    this.noFileUploaded = false;
    this.surveyFiles = Object.keys(pFileList.target.files).map((key:any) => pFileList.target.files[key]);
    console.log(this.surveyFiles.length);
    this.uploadFileEvent.emit(this.surveyFiles);
    
    
    this._snackBar.open("Successfully upload!", 'Close', {
      duration: 2000,
    });
  }

  deleteFile(f:any){
    this.surveyFiles = this.surveyFiles!.filter(function(w:any){ return w.name != f.name });
    this._snackBar.open("Successfully delete!", 'Close', {
      duration: 2000,
    });
  }

  openConfirmDialog(pIndex:any): void {
    const dialogRef = this.dialog.open(DeleteFileComponent, {
      panelClass: 'modal-xs',
    });
    dialogRef.componentInstance.fName = this.surveyFiles![pIndex].name;
    dialogRef.componentInstance.fIndex = pIndex;


    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        this.deleteFromArray(result);
      }
    });
  }

  deleteFromArray(index:any) {
    console.log(this.surveyFiles);
    this.surveyFiles!.splice(index, 1);
    if (this.surveyFiles!.length == 0) {
      this.noFileUploaded = true;
    }
  }

  dropHandler(event: any) {}
  dragOverHandler(event: any) {}
  dragLeaveHandler(event: any) {}
  dragEnterHandler(event: any) {}
}
