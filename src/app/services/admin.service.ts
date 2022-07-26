import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Admin } from '../models/admin';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private MOCK_URL_ADMIN = environment.MOCK_URL + '/admin';
  constructor(private http: HttpClient) {}

  getAdmin(): Observable<Admin> {
    return this.http.get<Admin>(this.MOCK_URL_ADMIN);
  }

  UpdateAdmin(newAdmin: Admin): Observable<Admin> {
    return this.http.patch<Admin>(this.MOCK_URL_ADMIN, newAdmin);
  }
}
