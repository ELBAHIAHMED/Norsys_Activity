import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PhotoRoutingModule } from './photo-routing.module';
import { ViewComponent } from './view/view.component';
import { GalleryComponent } from './components/gallery/gallery.component';
import {VisitorModule} from "../visitor/visitor.module";
import {NgxGalleryModule} from "@kolkov/ngx-gallery";
import {AdminModule} from "../admin/admin.module";
import { SidebarGalleryComponent } from './components/sidebar-gallery/sidebar-gallery.component';


@NgModule({
  declarations: [
    ViewComponent,
    GalleryComponent,
    SidebarGalleryComponent,

  ],
  imports: [
    CommonModule,
    PhotoRoutingModule,
    VisitorModule,
    NgxGalleryModule,
    AdminModule
  ]
})
export class PhotoModule { }
