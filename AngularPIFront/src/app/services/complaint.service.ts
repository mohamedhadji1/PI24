
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Complaint } from '../core/Complaint';
import { constantes } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

  private baseUrl = constantes.base_url+'api/complaint';

  constructor(private http: HttpClient) { }

  createComplaint(complaint: Complaint): Observable<Complaint> {
    return this.http.post<Complaint>(this.baseUrl, complaint);
  }
  getAllComplaints(): Observable<Complaint[]> {
    return this.http.get<Complaint[]>(this.baseUrl);
  }
  deleteComplaint(idComp: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${idComp}`);
  }
  updateComplaint(idComp: number, updatedComplaint: Complaint): Observable<Complaint> {
    const url = `${this.baseUrl}/${idComp}`;
    return this.http.put<Complaint>(url, updatedComplaint);
  }
  getComplaintById(idComp: number): Observable<Complaint> {
    const url = `${this.baseUrl}/${idComp}`;
    return this.http.get<Complaint>(url);
  }
  getComplaintByUserId(idUser: number): Observable<Complaint[]> {
    const url = `${this.baseUrl}/getAllByUserId/${idUser}`;
    return this.http.get<Complaint[]>(url);

  }

  



}
