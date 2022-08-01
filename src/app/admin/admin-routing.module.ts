import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './layout/admin.component';
import { ActivitiesComponent } from './view/activities/activities.component';
import { CollaboratorsComponent } from './view/collaborators/collaborators.component';
import { DashboardComponent } from "./view/dashboard/dashboard.component";
import { MapsComponent } from "./view/maps/maps.component";
import { SettingsComponent } from "./view/settings/settings.component";


const routes: Routes = [
  {
    path: "",
    component: AdminComponent,
    children: [
      { path: "dashboard", component: DashboardComponent },
      { path: "profile", component: SettingsComponent },
      { path: "activities", component: ActivitiesComponent },
      { path: "maps", component: MapsComponent },
      { path: "collaborators", component: CollaboratorsComponent },
      { path: "", redirectTo: "dashboard", pathMatch: "full" },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
  
})
export class AdminRoutingModule { }
