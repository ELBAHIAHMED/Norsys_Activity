import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './layout/admin.component';
import { SettingsComponent } from './view/settings/settings.component';
import { MapsComponent } from './view/maps/maps.component';
import { DashboardComponent } from './view/dashboard/dashboard.component';
import { CardProfileComponent } from './components/cards/card-profile/card-profile.component';
import { CardSettingsComponent } from './components/cards/card-settings/card-settings.component';
import { CardStatsComponent } from './components/cards/card-stats/card-stats.component';
import { CardTableCollaboratorComponent } from './components/cards/card-table-collaborator/card-table-collaborator.component';
import { CardBarChartComponent } from './components/cards/card-bar-chart/card-bar-chart.component';
import { CardLineChartComponent } from './components/cards/card-line-chart/card-line-chart.component';
import { MapExampleComponent } from './components/map-example/map-example.component';
import { TableDropdownComponent } from './components/dropdown/table-dropdown/table-dropdown.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { AdminNavbarComponent } from './components/navbar/admin-navbar/admin-navbar.component';
import { FooterAdminComponent } from './components/footer/footer-admin/footer-admin.component';
import { HeaderStatsComponent } from './components/header-stats/header-stats.component';
import { NotificationDropdownComponent } from './components/dropdown/notification-dropdown/notification-dropdown.component';
import { HttpClientModule } from '@angular/common/http';
import { AdminService } from '../services/admin.service';
import { CardTableActivitiesComponent } from './components/cards/card-table-activities/card-table-activities.component';
import { ActivityService } from '../services/activity.service';
import { CollaboratorService } from '../services/collaborator.service';
import { FormsModule } from '@angular/forms';
import { ActivitiesComponent } from './view/activities/activities.component';
import { CardActivitiesComponent } from './components/cards/card-activities/card-activities.component';
import { CardCollaboratorsComponent } from './components/cards/card-collaborators/card-collaborators.component';
import { CollaboratorsComponent } from './view/collaborators/collaborators.component';
import { UploadComponent } from './components/upload/upload.component';
import { FileSizePipe } from './pipes/file-size.pipe';
import { CardPolarChartComponent } from './components/cards/card-polar-chart/card-polar-chart.component';
import { CardRadarChartComponent } from './components/cards/card-radar-chart/card-radar-chart.component';


@NgModule({
  declarations: [
    AdminComponent,
    SettingsComponent,
    MapsComponent,
    DashboardComponent,
    CardProfileComponent,
    CardSettingsComponent,
    CardStatsComponent,
    CardTableCollaboratorComponent,
    CardBarChartComponent,
    CardLineChartComponent,
    MapExampleComponent,
    TableDropdownComponent,
    SidebarComponent,
    AdminNavbarComponent,
    FooterAdminComponent,
    HeaderStatsComponent,
    NotificationDropdownComponent,
    CardTableActivitiesComponent,
    ActivitiesComponent,
    CardActivitiesComponent,
    CardCollaboratorsComponent,
    CollaboratorsComponent,
    UploadComponent,
    FileSizePipe,
    CardPolarChartComponent,
    CardRadarChartComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [AdminService, ActivityService, CollaboratorService]
})
export class AdminModule { }
