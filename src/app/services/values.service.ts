import { Injectable } from '@angular/core';
import { Activity } from '../models/activity';
import { Admin } from '../models/admin';
import { Collaborator } from '../models/collaborator';

@Injectable({
  providedIn: 'root'
})
export class ValueService {
  private _collaborators: Collaborator[] = [];
  private _activities: Activity[] = [];
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

  set admin(admin: Admin) {
    this._admin = admin;
  }
  get admin() {
    return this._admin as Admin;
  }

  constructor() {}
}