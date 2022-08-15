import { Component, OnInit } from '@angular/core';
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  Events = [
    {
      type:'campus',
      name: 'FOOTBALL',
      date: 'october 9 2021',
      img:'https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg',
      description:'Its an event organised by norsys afrique in the second day of the campus',


    }, {
      type:'campus',
      name: 'FOOTBALL',
      date: 'october 9 2021',
      img:'https://media.gettyimages.com/photos/menara-pavilion-and-gardens-marrakesh-picture-id577088095?s=612x612',
      description:'Its an event organised by norsys afrique in the second day of the campus',


    }, {
      type:'campus',
      name: 'FOOTBALL',
      date: '2019-06-17',
      img:'https://img-19.commentcamarche.net/cI8qqj-finfDcmx6jMK6Vr-krEw=/1500x/smart/b829396acc244fd484c5ddcdcb2b08f3/ccmcms-commentcamarche/20494859.jpg',
      description:'Its an event organised by norsys afrique in the second day of the campus',


    }, {
      type:'campus',
      name: 'FOOTBALL',
      date: 'october 9 2021',
      img:'https://cdn.futura-sciences.com/buildsv6/images/mediumoriginal/6/5/2/652a7adb1b_98148_01-intro-773.jpg',
      description:'Its an event organised by norsys afrique in the second day of the campus',


    }, {
      type:'campus',
      name: 'FOOTBALL',
      date: 'october 9 2021',
      img:'https://img.olympicchannel.com/images/image/private/t_social_share_thumb/f_auto/primary/qjxgsf7pqdmyqzsptxju',
      description:'Its an event organised by norsys afrique in the second day of the campus',


    }
  ];

}
