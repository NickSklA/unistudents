import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { Chart } from 'chart.js';
import {GradeResults} from '../shared/models/grade-results.model';
import {Storage} from '@ionic/storage';
import {Student} from '../shared/models/student.model';
import {StorageService} from '../shared/services/storage.service';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page implements OnInit {
  @ViewChild('lineCanvas') lineCanvas: ElementRef;

  private lineChart: Chart;
  public grades: GradeResults;
  public student: Student;

  constructor(
      private storageService: StorageService
  ) {}

  ngOnInit(): void {
    this.loadStudentInfo();
  }

  loadStudentInfo() {
    this.storageService.getStudent()
        .then(
            (student) => {
              this.student = student;
              this.lineChart = this.getChart();
            }
        )
        .catch(
            error => console.log(error)
        )
        .finally(
        () => {
        }
    );
  }

  getChart(): Chart {

    const grades: Array<number> = [];
    const semesters: Array<number> = [];

    for (let i = 0; i < this.student.grades.semesters.length; i++) {
      grades.push(Number(this.student.grades.semesters[i].gradeAverage));
      semesters.push(this.student.grades.semesters[i].id);
    }

    return new Chart(this.lineCanvas.nativeElement, {
      type: 'line',
      data: {
        labels: semesters,
        datasets: [
          {
            label: 'GPA',
            fill: true,
            lineTension: 0.4,
            backgroundColor: 'rgba(101,123,255,0.41)',
            borderColor: '#657BFF',
            borderCapStyle: 'butt',
            borderDash: [],
            borderDashOffset: 0.0,
            borderJoinStyle: 'miter',
            pointBorderColor: 'rgba(75,192,192,1)',
            pointBackgroundColor: '#fff',
            pointBorderWidth: 1,
            pointHoverRadius: 5,
            pointHoverBackgroundColor: 'rgba(75,192,192,1)',
            pointHoverBorderColor: 'rgba(220,220,220,1)',
            pointHoverBorderWidth: 2,
            pointRadius: 1,
            pointHitRadius: 10,
            data: grades,
            spanGaps: false,
            duration: 4000,
            easing: 'easeInQuart'
          }
        ]
      },
      options: {
        scales: {
          xAxes: [{
            gridLines: {
              display: true
            }
          }],
          yAxes: [{
            gridLines: {
              drawBorder: false,
              display: false
            },
            ticks: {
              maxTicksLimit: 4
            }
          }]
        }
      },
    });
  }
}
