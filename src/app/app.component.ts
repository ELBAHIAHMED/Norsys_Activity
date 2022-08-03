import {Component, OnInit} from '@angular/core';
import { AdminService } from './services/admin.service';
// @ts-ignore
import AOS from 'aos';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'norsys-activity';



  ngOnInit(): void {
    AOS.init();

  }
}
