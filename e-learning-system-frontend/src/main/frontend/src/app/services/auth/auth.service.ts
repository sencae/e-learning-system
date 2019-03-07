import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

//import { SignUpInfo } from './signup-info';
import {AuthInfo} from "../../models/AuthInfo";
import {JwtResponse} from "../../models/JwtResponse";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = 'http://localhost:8080/login';
  private signupUrl = 'http://localhost:8080/api/auth/signup';

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: AuthInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  // signUp(info: SignUpInfo): Observable<string> {
  //   return this.http.post<string>(this.signupUrl, info, httpOptions);
  // }
}
