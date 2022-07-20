import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './view/login/login.component';
import { AuthComponent } from './components/layout/auth.component';
import { AuthNavbarComponent } from './components/navbar/auth-navbar/auth-navbar.component';
import { FooterSmallComponent } from './components/footer/footer-small/footer-small.component';


@NgModule({
  
  declarations: [
    LoginComponent,
    AuthComponent,
    AuthNavbarComponent,
    FooterSmallComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
  ]
})
export class AuthModule { }
