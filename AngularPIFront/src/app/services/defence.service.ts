import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { defense } from '../core/Defense';
import { ObjectId } from 'mongoose';

@Injectable({
  providedIn: 'root'
})
export class DefenceService {
  private baseUrl = 'http://localhost:8081/api/Defence';
  private baseUrll = 'http://localhost:8081/api';

  constructor(private http: HttpClient) {}

  getAllDefence(): Observable<defense[]> {
    return this.http.get<defense[]>(this.baseUrl);
  }

  createDefence(defence: defense): Observable<defense> {
    return this.http.post<defense>(this.baseUrl, defence);
  }

  deleteDefence(DefenceId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${DefenceId}`);
  }

  updateDefense(DefenceId: number, updatedefense: defense): Observable<defense> {
    const url = `${this.baseUrl}/${DefenceId}`;
    return this.http.put<defense>(url, updatedefense);
  }

  getTaskById(taskId: number): Observable<defense> {
    const url = `${this.baseUrl}/${taskId}`;
    return this.http.get<defense>(url);
  }

  getDefenses(): Observable<defense[]> {
    return this.http.get<defense[]>(`${this.baseUrl}/defenses`);
  }

 /* getUsedUserIds(): Observable<number[]> {
    // Appel HTTP pour récupérer les IDs des défenses déjà utilisées depuis un service externe
    return this.http.get<number[]>(`${this.baseUrl}/users`);
  }*/
  getAllDefenses(): Observable<defense[]> {
    const url = `${this.baseUrl}/defenses`;
    return this.http.get<defense[]>(url);
  }

}
    

