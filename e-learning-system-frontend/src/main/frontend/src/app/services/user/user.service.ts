import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../models/User";
import {TokenStorageService} from "../auth/token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) {
  }

  register(user: User) {
    return this.http.post(`/registration/user`, user);
  }

  hasAuthority(auth: string) {
    return this.tokenStorage.getAuthorities().every(
      authority => {
        return !(authority === auth);
      });
  }

}
