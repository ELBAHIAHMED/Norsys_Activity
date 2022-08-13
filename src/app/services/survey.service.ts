import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { forkJoin, Observable, switchMap, tap } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { environment } from 'src/environments/environment';
import { Survey } from '../models/survey';

@Injectable({
  providedIn: 'root',
})
export class SurveyService {
  MOCK_URL_SURVEYS = environment.MOCK_URL + '/surveys';
  constructor(private http: HttpClient, private toast: ToastrService) {}
  getAllSurveys(): Observable<Survey[]> {
    return this.http.get<Survey[]>(this.MOCK_URL_SURVEYS);
  }
  addSurvey(newSurvey: Survey): Observable<Survey> {
    return this.http.post<Survey>(this.MOCK_URL_SURVEYS, newSurvey).pipe(
      tap((res) => {
        if (res) {
          this.toast.success('Formulaire ajouté...', '', {
            timeOut: 1000,
          });
        }
      })
    );
  }
  deleteOneSurvey(survey_id: number): Observable<Survey> {
    console.log(`${this.MOCK_URL_SURVEYS}/${survey_id}`);

    return this.http
      .delete<Survey>(`${this.MOCK_URL_SURVEYS}/${survey_id}`)
      .pipe(
        tap((res) => {
          console.log(res);

          if (res) {
            this.toast.success('Formulaire supprimé...', '', {
              timeOut: 1000,
            });
          }
        })
      );
  }
  shareForm(survey: Survey, surveys: Survey[]): any {
    let shareFormRequest: Observable<any>[] = [];
    surveys.forEach((_survey) => {
      let notShareForm = this.http.patch<Survey>(
        `${this.MOCK_URL_SURVEYS}/${_survey.id}`,
        {
          IsAvailable: false,
        }
      );
      let ShareForm = this.http.patch<Survey>(
        `${this.MOCK_URL_SURVEYS}/${_survey.id}`,
        {
          IsAvailable: true,
        }
      );
      if (survey.id !== _survey.id) {
        _survey.IsAvailable = false;
        shareFormRequest.push(notShareForm);
      } else {
        _survey.IsAvailable = true;
        shareFormRequest.push(ShareForm);
      }
    });

    return forkJoin(shareFormRequest);
  }
  updateSurvey(newSurvey: Survey, id: number): Observable<Survey> {
    return this.http
      .patch<Survey>(`${this.MOCK_URL_SURVEYS}/${id}`, newSurvey)
      .pipe(
        tap((res) => {
          console.log(res);

          if (res) {
            this.toast.success('Formulaire actualisé...', '', {
              timeOut: 1000,
            });
          }
        })
      );
  }
}
