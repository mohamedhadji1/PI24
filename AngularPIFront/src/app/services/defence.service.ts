import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { defense } from '../core/Defense';
import { ObjectId } from 'mongoose';

@Injectable({
  providedIn: 'root'
})
export class DefenceService {
//readonly API_URL="http://localhost:8080"
//readonly ENDPOINT_api="/api"
private baseUrl = 'http://localhost:8081/api/Defence';

  constructor(private http: HttpClient) { 

  }
  getAllDefence():Observable<defense[]> {
  
 
      return this.http.get<defense[]>(this.baseUrl);
     }
     createDefence(defence: defense): Observable<defense> {
      return this.http.post<defense>(this.baseUrl, defence);
    }
   
    deleteDefence(DefenceId: number): Observable<void> {
      return this.http.delete<void>(`${this.baseUrl}/${DefenceId}`);
    }
    updatedefense(DefenceId: number, updatedefense: defense): Observable<defense> {
      const url = `${this.baseUrl}/${DefenceId}`;
      return this.http.put<defense>(url, updatedefense);
    }
}
