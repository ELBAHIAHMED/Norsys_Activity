import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PhotoRoutingModule } from './photo-routing.module';
import { ViewComponent } from './view/view.component';
import { GalleryComponent } from './components/gallery/gallery.component';
import {VisitorModule} from "../visitor/visitor.module";
import {NgxGalleryModule} from "@kolkov/ngx-gallery";
import {AdminModule} from "../admin/admin.module";
import { SidebarGalleryComponent } from './components/sidebar-gallery/sidebar-gallery.component';
import {VideoComponent} from "./components/video/video.component";
import {MatTabsModule} from "@angular/material/tabs";


@NgModule({
  declarations: [
    ViewComponent,
    GalleryComponent,
    SidebarGalleryComponent,
    VideoComponent,

  ],
  imports: [
    CommonModule,
    PhotoRoutingModule,
    VisitorModule,
    NgxGalleryModule,
    AdminModule,
    MatTabsModule
  ]
})
export class PhotoModule { }
