import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CollaboratorComponent } from './layout/collaborator.component';
import { ActivitiesComponent } from './view/activities/activities.component';
import { DashboardComponent } from './view/dashboard/dashboard.component';
import { FavoriteActivityComponent } from './view/favorite-activity/favorite-activity.component';
import { SettingsComponent } from './view/settings/settings.component';

const routes: Routes = [
  {
    path:"",
    component: CollaboratorComponent,
    children: [
      {path: "settings", component: SettingsComponent},
      {path: "activities", component: ActivitiesComponent}, 
      {path: "dashboard", component: DashboardComponent}, 
      {path: "favorite-activities", component: FavoriteActivityComponent}, 
      { path: "", redirectTo: "dashboard", pathMatch: "full" },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CollaboratorRoutingModule { }
