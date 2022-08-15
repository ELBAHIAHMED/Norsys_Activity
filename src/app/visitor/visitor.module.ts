import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VisitorRoutingModule } from './visitor-routing.module';
import { IndexComponent } from './view/index/index.component';
import { IndexNavbarComponent } from './components/navbar/index-navbar/index-navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { IndexDropdownComponent } from './components/dropdown/index-dropdown/index-dropdown.component';


@NgModule({
    declarations: [
        IndexComponent,
        IndexNavbarComponent,
        FooterComponent,
        IndexDropdownComponent
    ],
    exports: [
        IndexNavbarComponent
    ],
    imports: [
        CommonModule,
        VisitorRoutingModule
    ]
})
export class VisitorModule { }
