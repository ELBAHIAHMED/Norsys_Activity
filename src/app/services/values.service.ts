import { Injectable } from '@angular/core';
import { Activity } from '../models/activity';
import { Admin } from '../models/admin';
import { Collaborator } from '../models/collaborator';
import { Survey } from '../models/survey';

@Injectable({
  providedIn: 'root'
})
export class ValueService {
  private _collaborators: Collaborator[] = [];
  private _activities: Activity[] = [];
  private _surveys: Survey[] = [];
  private _admin: Admin | undefined;
  set collaborators(collaborators: Collaborator[]) {
    this._collaborators = collaborators;
  }
  get collaborators() {
    return this._collaborators;
  }

  set activities(activities: Activity[]) {
    this._activities = activities;
  }
  get activities() {
    return this._activities;
  }

  set surveys(surveys: Survey[]) {
    this._surveys = surveys;
  }
  get surveys() {
    return this._surveys;
  }

  set admin(admin: Admin) {
    this._admin = admin;
  }
  get admin() {
    return this._admin as Admin;
  }

  constructor() {}
}