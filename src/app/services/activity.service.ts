import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Activity } from '../models/activity';

@Injectable({
  providedIn: 'root'
})
export class ActivityService {
  private MOCK_URL_ACTIVITIES = environment.MOCK_URL + '/activities/';
  constructor(private http: HttpClient) { }

  getAllActivites(): Observable<Activity[]> {
    return this.http.get<Activity[]>(this.MOCK_URL_ACTIVITIES);
  }

  getOneActivity(id: number): Observable<Activity> {
    return this.http.get<Activity>(`${this.MOCK_URL_ACTIVITIES}/${id}`);
  }
}
