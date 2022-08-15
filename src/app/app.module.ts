import {BrowserModule} from "@angular/platform-browser";
import {APP_INITIALIZER, NgModule} from "@angular/core";

import {AppRoutingModule} from "./app-routing.module";
import {AppComponent} from "./app.component";
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {initializeKeycloak} from "./init/keycloak-init.factory";
import {AuthModule} from "./auth/auth.module";
import {AdminModule} from "./admin/admin.module";
import {CollaboratorModule} from "./collaborator/collaborator.module";
import {VisitorModule} from "./visitor/visitor.module";
import {EventModule} from "./event/event.module";
import {PhotoModule} from "./photo/photo.module";
import {NgxGalleryModule} from "@kolkov/ngx-gallery";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    KeycloakAngularModule,
    AuthModule,
    AdminModule,
    CollaboratorModule,
    VisitorModule,
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
