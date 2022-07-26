import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CollaboratorRoutingModule } from './collaborator-routing.module';
import { CollaboratorService } from '../services/collaborator.service';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    CollaboratorRoutingModule,
    HttpClientModule
  ],
  providers: [CollaboratorService]
})
export class CollaboratorModule { }
