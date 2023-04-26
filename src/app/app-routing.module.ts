import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: 'admin',
    loadChildren: () =>
      import('./admin/admin.module').then((mod) => mod.AdminModule),
  },
  {
    path: 'auth',
    loadChildren: () =>
      import('./auth/auth.module').then((mod) => mod.AuthModule),
  }
  {
    path: 'event',
    loadChildren: () =>
      import('./event/event.module').then((mod) => mod.EventModule),
  },{
    path: 'photo',
    loadChildren: () =>
      import('./photo/photo.module').then((mod) => mod.PhotoModule),
  },
  {
    path: 'collaborator',
    loadChildren: () =>
      import('./collaborator/collaborator.module').then(
        (mod) => mod.CollaboratorModule
      ),
  },
  {
    path: '',
    loadChildren: () =>
      import('./visitor/visitor.module').then((mod) => mod.VisitorModule),
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
