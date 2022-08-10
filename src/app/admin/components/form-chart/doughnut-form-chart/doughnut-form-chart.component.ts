import { Component, Input, OnInit } from '@angular/core';
import { Color } from 'ng2-charts';
import { Option } from 'src/app/models/option';
import { Question } from 'src/app/models/question';

@Component({
  selector: 'app-doughnut-form-chart',
  templateUrl: './doughnut-form-chart.component.html',
  styleUrls: ['./doughnut-form-chart.component.scss'],
})
export class DoughnutFormChartComponent implements OnInit {
  @Input()
  question!: Question;
  doughnutChartLabels: string[] = [];
  doughnutChartData: number[] = [];
  chartOptions: any = {
    responsive: true,
    maintainAspectRatio: false,
    legend: {
      position: 'right',
      labels: {
        fontSize: 14,
        usePointStyle: true,
      },
    },
  };
  constructor() {}

  ngOnInit() {
    this.question.options.forEach((option: Option) => {
      this.doughnutChartLabels.push(option.OptionText);
      this.doughnutChartData.push(Math.floor(Math.random() * 100));
    });
  }
}
