import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventRoutingModule } from './event-routing.module';
import { CardComponent } from './compoments/card/card.component';
import { FilterBarComponent } from './compoments/filter-bar/filter-bar.component';
import { ViewComponent } from './view/view.component';
import {VisitorModule} from "../visitor/visitor.module";


@NgModule({
  declarations: [

    CardComponent,
       FilterBarComponent,
       ViewComponent
  ],
  imports: [
    CommonModule,
    EventRoutingModule,
    VisitorModule
  ]
})
export class EventModule { }
