import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FileExchangeService {
  private uploadResUrl='/api/uploadres';
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
  uploadResourceFiles(files:File[],course:number):Observable<any>{
    const formData: FormData = new FormData();
    for (var i = 0; i < files.length; i++) {
      formData.append('files',files[i]);
    }
    formData.append('course',course.toString());
    return this.http.post(this.uploadResUrl,formData);
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
