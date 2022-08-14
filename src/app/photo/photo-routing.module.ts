import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ViewComponent} from "../photo/view/view.component";

const routes: Routes = [
  {path:"",component:ViewComponent},
  {path:"**",redirectTo:"",pathMatch:"full"},


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PhotoRoutingModule { }
