import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CollaboratorRoutingModule } from './collaborator-routing.module';
import { CollaboratorService } from '../services/collaborator.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ActivityService } from '../services/activity.service';
import { ValueService } from '../services/values.service';


@NgModule({
  declarations: [


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
