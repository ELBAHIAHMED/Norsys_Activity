import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './components/layout/admin.component';
import { DashboardComponent } from "./view/dashboard/dashboard.component";
import { MapsComponent } from "./view/maps/maps.component";
import { SettingsComponent } from "./view/settings/settings.component";
import { TablesComponent } from "./view/tables/tables.component";


const routes: Routes = [
  {
    path: "",
    component: AdminComponent,
    children: [
      { path: "dashboard", component: DashboardComponent },
      { path: "settings", component: SettingsComponent },
      { path: "tables", component: TablesComponent },
      { path: "maps", component: MapsComponent },
      { path: "", redirectTo: "dashboard", pathMatch: "full" },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
  
})
export class AdminRoutingModule { }
