import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../models/user/User";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  register(user: User) {
    return this.http.post(`/registration/user`, user);
  }

  login(email: string, password: string) {
    return this.http.post(`/login`, {email: email, password: password})
  }
}
