import { Injectable, Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../core/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = 'http://localhost:8081/api/users';

  constructor(private http: HttpClient) { }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.baseUrl, user);
  }
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl);
  }
  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${id}`);
  }
  updateUser(id: number, updatedUser: User): Observable<User> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.put<User>(url, updatedUser);
  }
  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
  
}