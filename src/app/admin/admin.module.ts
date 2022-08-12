import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

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
import { MatSnackBarModule } from '@angular/material/snack-bar';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './layout/admin.component';
import { SettingsComponent } from './view/settings/settings.component';
import { DashboardComponent } from './view/dashboard/dashboard.component';
import { CardProfileComponent } from './components/cards/card-profile/card-profile.component';
import { CardSettingsComponent } from './components/cards/card-settings/card-settings.component';
import { CardStatsComponent } from './components/cards/card-stats/card-stats.component';
import { CardTableCollaboratorComponent } from './components/cards/card-table-collaborator/card-table-collaborator.component';
import { CardBarChartComponent } from './components/cards/card-bar-chart/card-bar-chart.component';
import { CardLineChartComponent } from './components/cards/card-line-chart/card-line-chart.component';
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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivitiesComponent } from './view/activities/activities.component';
import { CardActivitiesComponent } from './components/cards/card-activities/card-activities.component';
import { CardCollaboratorsComponent } from './components/cards/card-collaborators/card-collaborators.component';
import { CollaboratorsComponent } from './view/collaborators/collaborators.component';
import { UploadComponent } from './components/upload/upload.component';
import { FileSizePipe } from './pipes/file-size.pipe';
import { CardPolarChartComponent } from './components/cards/card-polar-chart/card-polar-chart.component';
import { CardRadarChartComponent } from './components/cards/card-radar-chart/card-radar-chart.component';
import { FormulaireComponent } from './view/formulaire/formulaire.component';
import { SurveyComponent } from './components/survey/survey.component';
import { CardFormulaireComponent } from './components/cards/card-formulaire/card-formulaire.component';
import { SurveyService } from '../services/survey.service';
import { ConfirmationDialog } from './components/dialogs/confirmation-dialog/confirmation-dialog.component';
import { ToastrModule } from 'ngx-toastr';
import { FormStatisticsComponent } from './components/form-statistics/form-statistics.component';
import { MatMenuModule } from '@angular/material/menu';
import { LayoutModule } from '@angular/cdk/layout';
import { FormUploadComponent } from './components/form-upload/form-upload.component';
import { DeleteFileComponent } from './components/dialogs/delete-file/delete-file.component';
import { ClickStopPropagationDirective } from '../directives/click-stop-propagation.directive';
import { ChartsModule } from 'ng2-charts';
import { FileDragDropDirective } from '../directives/file-drag-drop.directive';
import { PieFormChartComponent } from './components/form-chart/pie-form-chart/pie-form-chart.component';
import { FormEditComponent } from './components/form-edit/form-edit.component';

@NgModule({
  declarations: [
    AdminComponent,
    SettingsComponent,
    DashboardComponent,
    CardProfileComponent,
    CardSettingsComponent,
    CardStatsComponent,
    CardTableCollaboratorComponent,
    CardBarChartComponent,
    CardLineChartComponent,
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
    CardRadarChartComponent,
    FormulaireComponent,
    SurveyComponent,
    CardFormulaireComponent,
    ConfirmationDialog,
    FormStatisticsComponent,
    FormUploadComponent, 
    DeleteFileComponent,
    ClickStopPropagationDirective,
    FileDragDropDirective,
    PieFormChartComponent,
    FormEditComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
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
    MatSnackBarModule,
    ToastrModule.forRoot({
      closeButton: true,
      progressBar: true,
      progressAnimation: 'increasing',
    }),
    MatMenuModule,
    LayoutModule,
    ChartsModule
  ],
  providers: [
    AdminService,
    ActivityService,
    CollaboratorService,
    SurveyService,
  ],
})
export class AdminModule {}
