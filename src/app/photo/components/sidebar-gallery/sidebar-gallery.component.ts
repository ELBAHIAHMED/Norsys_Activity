import { Component, OnInit } from '@angular/core';
import {GalleryService} from "../../gallery.service";

@Component({
  selector: 'app-sidebar-gallery',
  templateUrl: './sidebar-gallery.component.html',
  styleUrls: ['./sidebar-gallery.component.css']
})
export class SidebarGalleryComponent implements OnInit {
  collapseShow = "hidden";


  ngOnInit() {}
  toggleCollapseShow(classes: any) {
    this.collapseShow = classes;
  }
}
