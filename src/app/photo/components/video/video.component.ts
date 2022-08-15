import {Component, Input, OnInit} from '@angular/core';
import {GalleryService} from "../../gallery.service";
import {AnimationEvent} from "@angular/animations";

interface Item {
  imageSrc: string;
  imageAlt: string;
}

@Component({
  selector: 'app-video',
  templateUrl: './video.component.html',
  styleUrls: ['./video.component.css']
})

export class VideoComponent implements OnInit {

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
