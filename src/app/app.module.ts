import { BrowserModule } from "@angular/platform-browser";
import { APP_INITIALIZER, NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { HttpClientModule } from "@angular/common/http";
import { KeycloakAngularModule, KeycloakService } from "keycloak-angular";
import { initializeKeycloak } from "./init/keycloak-init.factory";
import { AuthModule } from "./auth/auth.module";
import { AdminModule } from "./admin/admin.module";
import { CollaboratorModule } from "./collaborator/collaborator.module";
import { VisitorModule } from "./visitor/visitor.module";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    KeycloakAngularModule,
    HttpClientModule,
    AuthModule,
    AdminModule,
    CollaboratorModule,
    VisitorModule
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
export class AppModule {}
