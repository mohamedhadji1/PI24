import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { evaluation } from '../core/Evaluation';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EvaluationService {
  private baseUrl = 'http://localhost:8081/api/Evaluation';
  constructor(private http: HttpClient) { }

  getAllEvaluation():Observable<evaluation[]> {
  
 
    return this.http.get<evaluation[]>(this.baseUrl);
   }
   createEvaluation(evaluation: evaluation): Observable<evaluation> {
    return this.http.post<evaluation>(this.baseUrl, evaluation);
  }
 
  deleteEvaluation(EvaluationId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${EvaluationId}`);
  }
  updateEvaluation(EvaluationId: number, updateEvaluation: evaluation): Observable<evaluation> {
    const url = `${this.baseUrl}/${EvaluationId}`;
    return this.http.put<evaluation>(url, updateEvaluation);
  }
  getTaskById(taskId: number): Observable<evaluation> {
    const url = `${this.baseUrl}/${taskId}`;
    return this.http.get<evaluation>(url);
  }
}
