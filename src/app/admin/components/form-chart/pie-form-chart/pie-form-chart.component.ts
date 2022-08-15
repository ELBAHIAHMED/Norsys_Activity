import {
  AfterContentInit,
  AfterViewChecked,
  Component,
  Input,
  OnInit,
} from '@angular/core';
import { Option } from 'src/app/models/option';
import { Question } from 'src/app/models/question';
import { Survey } from 'src/app/models/survey';
import { ValueService } from 'src/app/services/values.service';

@Component({
  selector: 'app-pie-form-chart',
  templateUrl: './pie-form-chart.component.html',
  styleUrls: ['./pie-form-chart.component.scss'],
})
export class PieFormChartComponent implements OnInit {
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
