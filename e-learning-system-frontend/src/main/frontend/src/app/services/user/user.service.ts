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
  private updateUserUrl='api/edit';
  private uploadImg='api/img';
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

  upload(file: File): Observable<string> {
    const formData: FormData = new FormData();
    formData.append('file', file);

    return this.http.post(this.uploadImg, formData, {
      reportProgress: false,
      responseType: 'text'
    });
  }
  updateUser(user:UserInfo){
    return this.http.post(this.updateUserUrl, user);
  }

}
