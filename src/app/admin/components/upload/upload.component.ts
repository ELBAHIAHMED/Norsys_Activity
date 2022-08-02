import {
  Component,
  ElementRef,
  OnInit,
  Renderer2,
  ViewChild,
} from '@angular/core';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css'],
})
export class UploadComponent implements OnInit {
  Files: any = [];
  noFileUploaded = true;
  objectURL: string | undefined;
  @ViewChild('gallery') gallery: ElementRef | undefined;

  constructor() {}

  ngOnInit(): void {}

  OnInputFileChange(event: any) {
    this.noFileUploaded = false;
    for (const file of event.target.files) {
      this.objectURL = URL.createObjectURL(file);
      console.log(this.objectURL);
      this.Files.push(file);
    }
  }

  dropHandler(event: any) {}
  dragOverHandler(event: any) {}
  dragLeaveHandler(event: any) {}
  dragEnterHandler(event: any) {}

  deleteFile(file: any) {
    this.Files.splice(this.Files.indexOf(file), 1);
    if (this.Files.length == 0) {
      this.noFileUploaded = true;
    }
  }
}
