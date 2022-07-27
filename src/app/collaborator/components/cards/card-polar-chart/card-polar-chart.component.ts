import { AfterViewInit, Component, OnInit } from '@angular/core';
import Chart from 'chart.js';

@Component({
  selector: 'app-card-polar-chart',
  templateUrl: './card-polar-chart.component.html',
  styles: [
  ]
})
export class CardPolarChartComponent implements OnInit,AfterViewInit {

  constructor() {}

  ngOnInit() {}
  ngAfterViewInit() {
    let config = {
      type: "polarArea",
      data: {
        labels: [
          "category1",
          "category2",
          "category3",
          "category4",
          "category5",
          "category6",
          "category7",
        ],
        datasets: [
          {
            label: (new Date().getFullYear()).toString(),
            backgroundColor: [
              'rgb(255, 99, 132)',
              'rgb(75, 192, 192)',
              'rgb(255, 205, 86)',
              'rgb(201, 203, 207)',
              'rgb(54, 162, 235)',
              'rgb(54, 162, 0)',
              'rgb(54, 0, 235)',
            ],
            borderColor: [],
            data: [120, 150, 200, 70, 180, 100, 130],
            fill: false,
            barThickness: 8,
          }
        ],
      },
      options: {
        maintainAspectRatio: false,
        responsive: true,
        title: {
          display: false,
          text: "Orders Chart",
        },
        tooltips: {
          mode: "index",
          intersect: false,
        },
        hover: {
          mode: "nearest",
          intersect: true,
        },
        legend: {
          labels: {
            fontColor: "rgba(0,0,0,.4)",
          },
          align: "end",
          position: "bottom",
        }
      },
    };
    let ctx: any = document.getElementById("polar-chart");
    ctx = ctx.getContext("2d");
    new Chart(ctx, config as any);
  }

}
