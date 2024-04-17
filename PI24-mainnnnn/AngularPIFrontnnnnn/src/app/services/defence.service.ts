import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { defense } from '../core/Defense';
import { HistoriqueDefense } from '../core/HistoriqueDefense';


@Injectable({
  providedIn: 'root'
})
export class DefenceService {

  private baseUrl = 'http://localhost:8081/api/Defence';
  private baseUrll = 'http://localhost:8081/api';
  
  private baseUrlHistorique = 'http://localhost:8081/api/Defence/historique';

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

  getAllDefenses(): Observable<defense[]> {
    const url = `${this.baseUrl}/defenses`;
    return this.http.get<defense[]>(url);
  }
  getUsedDates(): Observable<Date[]> {
    return this.http.get<Date[]>(`${this.baseUrl}/used-dates`);
  }
    getAllHistoriqueDefense() {
      const url = `${this.baseUrl}/getAllHistoriqueDefense`;
      return this.http.get<HistoriqueDefense[]>(url);
    }
    searchHistoriques(query: string): Observable<any[]> {
      return this.http.get<any[]>(`${this.baseUrl}/search?query=${query}`);
    }
    
    
   /* searchHistoriques(query: string): Observable<HistoriqueDefense[]> {
      return this.http.get<HistoriqueDefense[]>(`${this.baseUrl}/search?query=${query}`);
    }*/

   /*gethistoriqueDefenceByIdById( id :number) :Observable<HistoriqueDefense> {
    const url = `${this.baseUrlHistorique}/${id}`;
    return this.http.get<HistoriqueDefense>(url);
   }
 
  /*moveOldDefensesToHistory(): Observable<void> {
    return this.http.get<void>(`${this.baseUrlHistorique}`);
  }
  /*/
 /* transferDefensesToHistory(): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/transfer-to-history`, null);
  }
  moveOldDefensesToHistory(defenseId: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/transfer-to-history/${defenseId}`, null);
  }  */
  
 
  /*transferDefensesToHistory(): void {
    this.http.post<string>(`${this.baseUrl}/transfer-to-history`, null).subscribe(
      response => {
        console.log('Transfert réussi :', response);
        // Gérer la réponse de l'API ici, par exemple afficher un message de succès à l'utilisateur
      },
      error => {
        console.error('Erreur lors du transfert :', error);
        // Gérer les erreurs ici, par exemple afficher un message d'erreur à l'utilisateur
      }
    );
  }*/
}
