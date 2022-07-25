import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './components/layout/admin.component';
import { SettingsComponent } from './view/settings/settings.component';
import { MapsComponent } from './view/maps/maps.component';
import { DashboardComponent } from './view/dashboard/dashboard.component';
import { TablesComponent } from './view/tables/tables.component';
import { CardProfileComponent } from './components/cards/card-profile/card-profile.component';
import { CardSettingsComponent } from './components/cards/card-settings/card-settings.component';
import { CardSocialTrafficComponent } from './components/cards/card-social-traffic/card-social-traffic.component';
import { CardStatsComponent } from './components/cards/card-stats/card-stats.component';
import { CardTableCollaboratorComponent } from './components/cards/card-table-collaborator/card-table-collaborator.component';
import { CardBarChartComponent } from './components/cards/card-bar-chart/card-bar-chart.component';
import { CardLineChartComponent } from './components/cards/card-line-chart/card-line-chart.component';
import { CardPageVisitsComponent } from './components/cards/card-page-visits/card-page-visits.component';
import { MapExampleComponent } from './components/map-example/map-example.component';
import { TableDropdownComponent } from './components/dropdown/table-dropdown/table-dropdown.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { AdminNavbarComponent } from './components/navbar/admin-navbar/admin-navbar.component';
import { FooterAdminComponent } from './components/footer/footer-admin/footer-admin.component';
import { HeaderStatsComponent } from './components/header-stats/header-stats.component';
import { NotificationDropdownComponent } from './components/dropdown/notification-dropdown/notification-dropdown.component';
import { UserDropdownComponent } from './components/dropdown/user-dropdown/user-dropdown.component';
import { HttpClientModule } from '@angular/common/http';
import { AdminService } from '../services/admin.service';
import { CardTableActivitiesComponent } from './components/cards/card-table-activities/card-table-activities.component';
import { ActivityService } from '../services/activity.service';


@NgModule({
  declarations: [
    AdminComponent,
    SettingsComponent,
    MapsComponent,
    DashboardComponent,
    TablesComponent,
    CardProfileComponent,
    CardSettingsComponent,
    CardSocialTrafficComponent,
    CardStatsComponent,
    CardTableCollaboratorComponent,
    CardBarChartComponent,
    CardLineChartComponent,
    CardPageVisitsComponent,
    MapExampleComponent,
    TableDropdownComponent,
    SidebarComponent,
    AdminNavbarComponent,
    FooterAdminComponent,
    HeaderStatsComponent,
    NotificationDropdownComponent,
    UserDropdownComponent,
    CardTableActivitiesComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    HttpClientModule
  ],
  providers: [AdminService, ActivityService]
})
export class AdminModule { }
