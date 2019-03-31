import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Course} from "../../models/Course";
import {CreateCourse} from "../../models/CreateCourse";
import {TokenStorageService} from "../auth/token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class CourseService {
private allCoursesUrl = 'api/courses/all';
private createCourseUrl ='courses/create';
private getCourseUrl='api/course/';
private getMyCoursesUrl='api/my';
  constructor(private http: HttpClient) { }

  getAllCourses(): Observable<Course[]>{
    return this.http.get<Course[]>(this.allCoursesUrl)
  }
  createCourse(course:CreateCourse):Observable<number>{
    return this.http.post<number>(this.createCourseUrl,course)
  }
  getCourse(id:number): Observable<Course>{
    return this.http.get<Course>(this.getCourseUrl+id)
  }
  getMyCourses():Observable<Course[]>{
    return this.http.get<Course[]>(this.getMyCoursesUrl)
  }
}
