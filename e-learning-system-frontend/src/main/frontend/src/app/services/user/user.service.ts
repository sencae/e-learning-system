import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
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
  upload(file:File):Observable<HttpEvent<{}>>{
    const formData: FormData = new FormData();
    formData.append('file', file);
    const req = new HttpRequest('POST', this.uploadImg, formData, {
      reportProgress: true,
      responseType: 'text'
    });
    return this.http.request(req);
  }
  updateUser(user:UserInfo){
    this.upload(user.file).subscribe(event=>console.log(event));
    return this.http.post(this.updateUserUrl,user)
  }

}
