import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserInfo} from "../../models/UserInfo";
import {TokenStorageService} from "../auth/token-storage.service";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private getUserUrl='api/user/';
  private updateUserUrl='/api/edit';
  private isJoinUrl='api/check';
  private isAuthorUrl='api/isauthor';
  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) {
  }

  getUser(id:number): Observable<UserInfo>{
    return this.http.get<UserInfo>(this.getUserUrl+id)
  }

  hasAuthority(auth: string) {
    return this.tokenStorage.getAuthorities().every(
      authority => {
        return !(authority === auth);
      });
  }
  updateUser(user:UserInfo){
    return this.http.post(this.updateUserUrl,user)
  }
  isJoin(courseId:number): Observable<boolean>{
    return this.http.post<boolean>(this.isJoinUrl,courseId)
  }
  isAuthor(courseId:number): Observable<boolean>{
    return this.http.post<boolean>(this.isAuthorUrl,courseId)
  }
}
