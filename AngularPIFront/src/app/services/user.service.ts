import { Injectable, Component } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FormGroup } from '@angular/forms';
import { RegUser } from '../pages/authentication/RegUser';
import { User } from '../core/User';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = 'http://localhost:8081/api/users';
  private AuthURL = "http://localhost:8081/api/auth/signin"
  private VerifURL = "http://localhost:8081/api/auth/verif"

  private RegURL = "http://localhost:8081/api/auth/signup"

  rollls!:any

  constructor(private http: HttpClient,private router: Router) { }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.baseUrl, user);
  }
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl);
  }
  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${id}`);
  }
  updateUser(id: any, updatedUser: User): Observable<User> {
    console.warn(updatedUser)
    const url = `${this.baseUrl}/${id}`;
    return this.http.put<User>(url, updatedUser);
  }
  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  login(loginForm:FormGroup):Observable<HttpResponse<any>>{
    console.log(loginForm.value)
    return this.http.post<any>(`${this.AuthURL}`,loginForm.value);
  }
  public getToken(): string {
    return localStorage.getItem('token') ?? '';
  }

  register(user:RegUser){
    console.log(user)
    return this.http.post<any>(`${this.RegURL}`,user);
  }

  public setRoles(response: any) {
    // Check if the response object contains the 'roles' property
    if (!response || !response.roles || !Array.isArray(response.roles)) {
      this.rollls =  [];
    }

    // Extract roles from the response object
    const roles: string[] = response.roles;

    this.rollls = roles;
  }

  getRoles(){
    return this.rollls
  }
  public clear() {
    localStorage.clear();
  }

  public logOut(){
    return localStorage.removeItem('token')
  }
  logoutUser(){
    this.logOut();
    this.clear();
    this.router.navigate([''])
  }

  verifyToken(token:String):Observable<HttpResponse<any>>{
    return this.http.get<any>(`${this.VerifURL}/${token}`);
  }
}
