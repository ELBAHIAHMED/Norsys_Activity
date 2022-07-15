import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardNorsysActivityComponent } from './dashboard-norsys-activity/dashboard-norsys-activity.component';
import { AuthGuard } from './guard/auth.guard';

const routes: Routes = [
  { path: 'dashboard', component: DashboardNorsysActivityComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
