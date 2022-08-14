import {EventEmitter, Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GalleryService {
  showmaskemmiter = new EventEmitter<boolean>();
  showMask = false;

  getshowMask(){
    return this.showMask;
  }
}
