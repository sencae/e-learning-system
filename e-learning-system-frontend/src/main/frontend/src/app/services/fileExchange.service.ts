import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FileExchangeService {

  constructor(private http:HttpClient) { }

  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', '/api/upload', formData, {
      reportProgress: true,
      responseType: 'text'
    });
    return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get('/getallfiles');
  }
  deleteProfileImage(url:string){
    return this.http.post('/api/delete/profimg',url)
  }
  deleteResource(url:string){
    return this.http.post('/api/delete/courseres',url)
  }
}
