import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import {AuthInfo} from "../../models/AuthInfo";
import {JwtResponse} from "../../models/JwtResponse";
import {SignUpInfo} from "../../models/SignUpInfo";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = '/login';
  private signupUrl = 'registration/user';

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: AuthInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo){
    return this.http.post(this.signupUrl, info, httpOptions);
  }
}
