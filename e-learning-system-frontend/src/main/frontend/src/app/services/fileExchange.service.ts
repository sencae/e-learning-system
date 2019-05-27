import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FileExchangeService {
  private uploadResUrl='/api/uploadres';
  private uploadCourseImgUrl = '/api/uploadCourseImg';
  private uploadResLinkUrl='/api/uploadreslink';
  private uploadFinalTaskUrl='/api/uploadFinalTask';
  private uploadTaskFileUrl='/api/uploadTaskFile';
  constructor(private http:HttpClient) { }

  uploadResourceFiles(files:File[],topic:number):Observable<string>{
    const formData: FormData = new FormData();
    for (var i = 0; i < files.length; i++) {
      formData.append('files',files[i]);
    }
    formData.append('topic',topic.toString());
    return this.http.post<string>(this.uploadResUrl,formData);
  }
  uploadResourceLinks(resources:any, topicId: number){
    let data = {
      links: resources.links,
      topicId: topicId
    };
    return this.http.post(this.uploadResLinkUrl, data)
  }
  uploadCourseImg(file: File, courseId: number): Observable<string> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('id', courseId.toString());
    return this.http.post(this.uploadCourseImgUrl, formData, {
      reportProgress: false,
      responseType: 'text'
    });
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
  deleteTaskFile(url:string){
    return this.http.post('/api/delete/TaskFile',url)
  }
  uploadFinalTask(file:File, courseId:number){
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('id', courseId.toString());
    return this.http.post(this.uploadFinalTaskUrl, formData, {
      reportProgress: false,
      responseType: 'text'
    });
  }
  uploadTaskFile(file:File,courseId:number){
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('id', courseId.toString());
    return this.http.post(this.uploadTaskFileUrl, formData, {
      reportProgress: false,
      responseType: 'text'
    });
  }

  deleteCourseImage(url: string) {
    return this.http.post('/api/delete/courseimg', url)
  }
}
