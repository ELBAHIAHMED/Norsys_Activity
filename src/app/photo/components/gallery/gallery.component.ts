import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {animate, AnimationEvent, style, transition, trigger} from "@angular/animations";
import {GalleryService} from "../../gallery.service";

interface Item {
  imageSrc: string;
  imageAlt: string;
}

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css'],
  animations: [
    trigger('animation', [
      transition('void => visible', [
        style({transform: 'scale(0.5)'}), animate('150ms', style({transform: 'scale(1)'}))
      ]),
      transition('visible => void', [
        style({transform: 'scale(1)'}), animate('150ms', style({transform: 'scale(0.5)'}))
      ]),
    ]),
  ]
})
export class GalleryComponent implements OnInit {

  @Input() galleryData: Item[] = [];
  @Input() showCount = false;
  previewImage = false;
  showmask:boolean;
  currentLightboxImage: Item = this.galleryData[0];
  currentIndex = 0;
  controls = true;
  totalImageCount = 0;

  constructor( private galleryservice:GalleryService) {
  }

  ngOnInit(): void {
    this.totalImageCount = this.galleryData.length;

  }

  onPreviewImage(index: number): void {
    this.galleryservice.showMask = true;
    this.showmask=this.galleryservice.getshowMask();
    this.previewImage = true;
    this.currentIndex = index;
    this.currentLightboxImage = this.galleryData[index];
  }

  onAnimationEnd(event: AnimationEvent) {
    if (event.toState === 'void') {

    }

  }

  onClosePreview() {
    this.galleryservice.showMask = false;
    this.showmask=this.galleryservice.getshowMask();
    this.previewImage = false;
  }

  prev():void {
    this.currentIndex=this.currentIndex-1;
    if(this.currentIndex> 0){
      this.currentIndex=this.galleryData.length-1;
    }
    this.currentLightboxImage=this.galleryData[this.currentIndex];
  }


  next():void {
    this.currentIndex=this.currentIndex+1;
    if(this.currentIndex> this.galleryData.length-1){
      this.currentIndex=0;
    }
    this.currentLightboxImage=this.galleryData[this.currentIndex];
  }

  onSelected() {
    this.galleryservice.showmaskemmiter.emit(this.showmask);
  }
}
