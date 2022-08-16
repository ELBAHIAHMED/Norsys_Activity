import {BrowserModule} from "@angular/platform-browser";
import {APP_INITIALIZER, NgModule} from "@angular/core";
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDialogModule } from '@angular/material/dialog';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatRadioModule } from '@angular/material/radio';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatBadgeModule } from '@angular/material/badge';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';





import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { KeycloakAngularModule, KeycloakService } from "keycloak-angular";
import { initializeKeycloak } from "./init/keycloak-init.factory";
import { AuthModule } from "./auth/auth.module";
import { AdminModule } from "./admin/admin.module";
import { CollaboratorModule } from "./collaborator/collaborator.module";
import { VisitorModule } from "./visitor/visitor.module";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {EventModule} from "./event/event.module";
import {PhotoModule} from "./photo/photo.module";

import {NgxGalleryModule} from "@kolkov/ngx-gallery";


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    KeycloakAngularModule,
    AuthModule,
    AdminModule,
    CollaboratorModule,
    VisitorModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCheckboxModule,
    MatSidenavModule,
    MatToolbarModule,
    MatListModule,
    MatCardModule,
    MatTabsModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    MatDialogModule,
    MatExpansionModule,
    MatRadioModule,
    MatTooltipModule,
    MatAutocompleteModule,
    MatSlideToggleModule,
    MatGridListModule,
    MatBadgeModule,
    EventModule,
    PhotoModule,
    BrowserAnimationsModule,
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService],
    }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
