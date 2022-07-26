import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { AdminComponent } from "./admin/components/layout/admin.component";

const routes: Routes = [
  {
    path: "admin",
    loadChildren: () => import('./admin/admin.module').then(mod => mod.AdminModule)
  },
  {
    path: "auth",
    loadChildren: () => import('./auth/auth.module').then(mod => mod.AuthModule)
  },
  {
    path: "",
    loadChildren: () => import('./visitor/visitor.module').then(mod => mod.VisitorModule)
  },
  {
    path: "collaborator",
    loadChildren: () => import('./collaborator/collaborator.module').then(mod => mod.CollaboratorModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
