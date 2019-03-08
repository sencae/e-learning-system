import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../models/User";
import {TokenStorageService} from "../auth/token-storage.service";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private getUserUrl='api/user/';
  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) {
  }

  getUser(id:number): Observable<User>{
    return this.http.get<User>(this.getUserUrl+id)
  }

  hasAuthority(auth: string) {
    return this.tokenStorage.getAuthorities().every(
      authority => {
        return !(authority === auth);
      });
  }

}
