import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Offer } from '../core/Offer';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OfferService {
  private baseUrl = 'http://localhost:8081/api/offers';
  constructor(private http: HttpClient) { 
    
  }
  getAllOffers(): Observable<Offer[]> {
    return this.http.get<Offer[]>(this.baseUrl);
  }
  getOffersByCompany(id: number): Observable<Offer[]> {
    return this.http.get<Offer[]>(`${this.baseUrl}/company/${id}`);
  }
  deleteOffer(id: number): void {
    this.http.delete(this.baseUrl);
  }
}