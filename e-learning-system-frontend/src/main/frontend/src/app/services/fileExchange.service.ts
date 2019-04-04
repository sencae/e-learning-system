import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FileExchangeService {
  private uploadResUrl='/api/uploadres';
  constructor(private http:HttpClient) { }

  uploadResourceFiles(files:File[],topic:number):Observable<string>{
    const formData: FormData = new FormData();
    for (var i = 0; i < files.length; i++) {
      formData.append('files',files[i]);
    }
    formData.append('topic',topic.toString());
    return this.http.post<string>(this.uploadResUrl,formData);
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
