import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CollaboratorRoutingModule } from './collaborator-routing.module';
import { CollaboratorService } from '../services/collaborator.service';
import { HttpClientModule } from '@angular/common/http';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { NotificationDropdownComponent } from './components/dropdown/notification-dropdown/notification-dropdown.component';
import { UserDropdownComponent } from './components/dropdown/user-dropdown/user-dropdown.component';
import { CollaboratorComponent } from './layout/collaborator.component';
import { FooterCollaboratorComponent } from './components/footer/footer-admin/footer-collaborator.component';
import { CollaboratorNavbarComponent } from './components/navbar/collaborator-navbar/collaborator-navbar.component';
import { HeaderStatsComponent } from './components/header-stats/header-stats.component';
import { CardStatsComponent } from './components/cards/card-stats/card-stats.component';
import { CardProfileComponent } from './components/cards/card-profile/card-profile.component';
import { CardSettingsComponent } from './components/cards/card-settings/card-settings.component';
import { FormsModule } from '@angular/forms';
import { SettingsComponent } from './view/settings/settings.component';
import { ActivitiesComponent } from './view/activities/activities.component';
import { CardTableActivitiesComponent } from './components/cards/card-table-activities/card-table-activities.component';
import { TableDropdownComponent } from './components/dropdown/table-dropdown/table-dropdown.component';
import { ActivityService } from '../services/activity.service';
import { ValueService } from '../services/values.service';
import { DashboardComponent } from './view/dashboard/dashboard.component';
import { FavoriteActivityComponent } from './view/favorite-activity/favorite-activity.component';
import { CardBarChartComponent } from './components/cards/card-bar-chart/card-bar-chart.component';
import { CardLineChartComponent } from './components/cards/card-line-chart/card-line-chart.component';
import { CardRadarChartComponent } from './components/cards/card-radar-chart/card-radar-chart.component';
import { CardPolarChartComponent } from './components/cards/card-polar-chart/card-polar-chart.component';
import { CardActivitiesComponent } from './components/cards/card-activities/card-activities.component';


@NgModule({
  declarations: [
    SidebarComponent,
    NotificationDropdownComponent,
    UserDropdownComponent,
    CollaboratorComponent,
    FooterCollaboratorComponent,
    CollaboratorNavbarComponent,
    HeaderStatsComponent,
    CardStatsComponent,
    CardProfileComponent,
    CardBarChartComponent,
    CardLineChartComponent,
    CardSettingsComponent,
    SettingsComponent,
    ActivitiesComponent,
    CardTableActivitiesComponent,
    TableDropdownComponent,
    DashboardComponent,
    FavoriteActivityComponent,
    CardRadarChartComponent,
    CardPolarChartComponent,
    CardActivitiesComponent
  ],
  imports: [
    CommonModule,
    CollaboratorRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [CollaboratorService,ActivityService,ValueService]
})
export class CollaboratorModule { }
