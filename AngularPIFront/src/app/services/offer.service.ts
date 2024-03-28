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
  deleteOffer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
  updateOffer(id: number, updatedOffer: Offer): Observable<Offer> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.put<Offer>(url, updatedOffer);
  }
  addOffer(formData: any, company_id: number): Observable<Offer> {
    console.log(`${this.baseUrl}/post/${company_id}`);
    return this.http.post<Offer>(`${this.baseUrl}/post/${company_id}`, formData);
  }
}
