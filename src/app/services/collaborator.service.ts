import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Collaborator } from 'src/app/models/collaborator';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CollaboratorService {
  private MOCK_URL_COLLABORATORS = environment.MOCK_URL + '/collaborators/';

  constructor(private http: HttpClient) { }

  getAllCollaborators(): Observable<Collaborator[]> {
    return this.http.get<Collaborator[]>(this.MOCK_URL_COLLABORATORS);
  }

  getOneCollaborator(id: number): Observable<Collaborator> {
    return this.http.get<Collaborator>(`${this.MOCK_URL_COLLABORATORS}/${id}`);
  }
  UpdateCollaborator(newCollaborator: Collaborator, id:number): Observable<Collaborator> {
    return this.http.patch<Collaborator>(this.MOCK_URL_COLLABORATORS, newCollaborator);
  }
}
