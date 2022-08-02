import { AfterViewInit, Component, OnInit } from '@angular/core';
import Chart from 'chart.js';

@Component({
  selector: 'app-card-radar-chart',
  templateUrl: './card-radar-chart.component.html',
})
export class CardRadarChartComponent implements OnInit,AfterViewInit {

  constructor() {}

  ngOnInit() {}
  ngAfterViewInit() {
    let config = {
      type: "radar",
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
            backgroundColor: "#edf4a6",
            borderColor: "#ed64a6",
            data: [12, 15, 20, 7, 18, 10, 13],
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
    let ctx: any = document.getElementById("radar-chart");
    ctx = ctx.getContext("2d");
    new Chart(ctx, config as any);
  }

}
